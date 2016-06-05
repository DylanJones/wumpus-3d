package display;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import entity.Entity;
import entity.EntityMolblin;
import entity.EntityOctorokRed;
import entity.EntitySpider;
import entity.Player;

/** This is a class of constants. It will contain global variables. */
public final class World {
	// Game state: 0 = overworld, 1 = bossfight, 2 = dead, 3 = dungeon
	// 4 = title screen / loading
	public static final int WORLD_HEIGHT = 11;
	public static final int WORLD_WIDTH = 16;

	private static int gameState = 0;
	private static Set<Entity> entities = new HashSet<Entity>();
	private static WorldTile[][] tiles = new WorldTile[WORLD_WIDTH][WORLD_HEIGHT];
	private static Player thePlayer;
	private static Timer ticker;
	private static String worldName;
	private static String northWorld;
	private static String southWorld;
	private static String eastWorld;
	private static String westWorld;

	static {
		thePlayer = new Player();
	}

	/**
	 * This method adds an entity to the world. It should only be called from
	 * the Entity constructor.
	 * 
	 * @param e
	 *            the Entity to add
	 */
	public static void registerEntity(Entity e) {
		entities.add(e);
	}

	/**
	 * For drawing methods. Given the abstract float coordinates x and y, return
	 * the coordinates for where on the screen to draw.
	 * 
	 * @param x
	 *            The x coordinate (in-world)
	 * @param y
	 *            The y coordinate (in-world)
	 * @return int[] of {x, y} which tells you where on the screen to draw
	 */
	public static int[] getScreenCoordinates(double x, double y) {
		return new int[] { (int) (Math.round(x * 32)),
				(int) (Math.round(y * 32 + 112)) };
	}

	/**
	 * Get the WorldTile at the specified x,y coordiantes
	 * 
	 * @param x
	 *            the x coordinate of tile
	 * @param y
	 *            the y coordinate of tile
	 * @return the WorldTile at specified location
	 */
	public static WorldTile getTileAt(int x, int y) {
		if (x >= 0 && y >= 0 && x < tiles.length && y < tiles[0].length)
			if (tiles[x][y] != null)
				return tiles[x][y];
		return WorldTile.ground;
	}

	/**
	 * Get the WorldTile at the specified x,y coordiantes
	 * 
	 * @param x
	 *            the x coordinate of tile
	 * @param y
	 *            the y coordinate of tile
	 * @return the WorldTile at specified location
	 */
	public static WorldTile getTileAt(double x, double y) {
		int nx = (int) Math.floor(x);
		int ny = (int) Math.floor(y);
		return getTileAt(nx, ny);
	}

	/**
	 * Call this method to delete an entity from the world.
	 * 
	 * @param e
	 *            the Entity to delete
	 */
	public static void deregisterEntity(Entity e) {
		entities.remove(e);
	}

	/** @return a Set view of all current Entities */
	public static Set<Entity> getAllEntities() {
		return new HashSet<Entity>(entities);
	}

	/**
	 * @return the gameState
	 */
	public static int getGameState() {
		return gameState;
	}

	/**
	 * @param gameState
	 *            the new GameState
	 */
	public static void setGameState(int gameState) {
		if (gameState == 2)
			World.stopTicker();
		World.gameState = gameState;
	}

	/**
	 * @return The player for the current world.
	 */
	public static Player getThePlayer() {
		return thePlayer;
	}

	/**
	 * Get the name of the current world.
	 * 
	 * @return the name of the current world
	 * */
	public static String getWorldName() {
		return worldName;
	}

	/**
	 * Called when the player walks off the screen, loads the new section of
	 * world in the specified direction
	 */
	public static void loadWorld(Direction direction) {
		switch (direction) {
		case NORTH:
			loadWorld(northWorld);
			break;
		case SOUTH:
			loadWorld(southWorld);
			break;
		case EAST:
			loadWorld(eastWorld);
			break;
		case WEST:
			loadWorld(westWorld);
			break;
		default:
			System.err.println("Invalid world direction " + direction);
			System.exit(1);
		}
	}

	public static void loadWorld(String filename) {
		// Delete all entites and tiles
		System.out.println("Loading world " + filename);
		SaveLoader.onWorldLeave();
		entities = new HashSet<Entity>();
		entities.add(thePlayer);
		worldName = filename;
		Scanner theScanner = null;
		try {
			theScanner = new Scanner(new File("assets/worlds/" + filename));
		} catch (IOException e) {
			System.err.println("Error reading file assets/worlds/" + filename);
			System.exit(1);
		}
		String tileFileName = theScanner.nextLine().replace("tiles ", "");
		loadTiles(tileFileName);
		String musicName = theScanner.nextLine().replace("music ", "");
		MusicPlayer.changePlayingMusic("assets/music/" + musicName);
		northWorld = theScanner.nextLine().replace("north ", "");
		southWorld = theScanner.nextLine().replace("south ", "");
		eastWorld = theScanner.nextLine().replace("east ", "");
		westWorld = theScanner.nextLine().replace("west ", "");
		if (!SaveLoader.hasVisited(worldName)) {
			// Load entites from file
			try {
				String line = "";
				do {
					line = theScanner.nextLine();
				} while (!line.equals("entities:"));
				while (theScanner.hasNext()) {
					createEntity(theScanner.nextLine());
				}
			} catch (NoSuchElementException e) {
			}
		} else {
			SaveLoader.loadEntities(worldName);
		}
	}

	private static void createEntity(String entityLine) {
		String[] elements = entityLine.split(" ");
		elements[0] = elements[0].toLowerCase();
		double[] coords = randomEmptyCoordinates();
		switch (elements[0]) {
		case "octorokred":
			new EntityOctorokRed(coords[0], coords[1]);
			break;
		case "spider":
			new EntitySpider(coords[0], coords[1]);
			break;
		case "molblin":
			new EntityMolblin(coords[0], coords[1]);
			break;
		default:
			System.err.println("WARNING: Unknown entity type \"" + elements[0]
					+ "\"!");
		}
	}

	private static double[] randomEmptyCoordinates() {
		double x, y;
		do {
			x = Math.random() * WORLD_WIDTH;
			y = 2 + (Math.random() * WORLD_HEIGHT - 3);
		} while (World.getTileAt(x, y).isSolid());
		return new double[] { x, y };
	}

	private static void loadTiles(String filename) {
		Scanner s = null;
		try {
			s = new Scanner(new File("assets/worlds/tiles/" + filename));
		} catch (FileNotFoundException e) {
		}
		for (int y = 0; y < WORLD_HEIGHT; y++) {
			String[] nums = s.nextLine().split("\\s+");
			for (int x = 0; x < WORLD_WIDTH; x++) {
				tiles[x][y] = WorldTile.getTileFromCode(Integer.parseInt(
						nums[x], 16));
			}
		}
	}

	public static void renderTiles(Graphics g) {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) {
				World.getTileAt(x, y).draw(x, y, g);
			}
		}
	}

	public static void startTicker(JPanel parent, KeyboardHandler kb) {
		ticker = new Timer(33, new Tick(parent, kb));
		ticker.start();
	}

	public static void stopTicker() {
		ticker.stop();
	}

	@SuppressWarnings("incomplete-switch")
	public static boolean willCollideTile(Entity e, double movement) {
		double[] newCoords = e.getFacing().moveInDirection(e.getX(), e.getY(),
				movement);
		double newX = newCoords[0];
		double newY = newCoords[1];
		switch (e.getFacing()) {
		case SOUTH:
			newY += e.getHeight() / 2;
			break;
		case EAST:
			newX += e.getWidth() / 2;
			break;
		case WEST:
			newX -= e.getWidth() / 2;
			break;
		}
		if (!(e instanceof Player))
			if (newX < 0 || newX >= WORLD_WIDTH || newY < 0
					|| newY >= WORLD_HEIGHT)
				return true;
		return World.getTileAt(newX, newY).isSolid();
	}

	/** World may not be instantiated. */
	private World() {
	}
}
