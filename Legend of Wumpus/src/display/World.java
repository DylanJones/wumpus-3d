package display;

import javax.swing.Timer;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;

/** This is a class of constants. It will contain global variables. */
// @SuppressWarnings("unused")
public final class World {
	// Game state: 0 = overworld, 1 = bossfight, 2 = dead, 3 = dungeon
	// 4 = title screen / loading
	private static int gameState = 0;
	private static Image backgroundImage;
	private static HashSet<Entity> entities = new HashSet<Entity>();
	private static HashSet<Wall> walls = new HashSet<Wall>();
	private static Player thePlayer;
	private static Timer ticker;
	private static WorldTemplate worldTemplate;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	static {
		thePlayer = new Player();
	}

	/**
	 * This method adds an entity to the world. It should only be called from
	 * the Entity constructor.
	 * 
	 * @param e
	 *            the Entity to delete
	 */
	public static void registerEntity(Entity e) {
		entities.add(e);
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
		World.gameState = gameState;
	}

	/**
	 * @return The player for the current world.
	 */
	public static Player getThePlayer() {
		return thePlayer;
	}

	/**
	 * Called when the player walks off the screen, loads the new section of
	 * world in the specified direction
	 */
	public static void loadWorld(int direction) {
		System.out.println("Someone fix this");
	}

	public static void loadWorld(String worldFile) {
		/* Open and read file... */
		/* PLEASE FIX THIS!!! YOU CAN DELETE WHATEVER YOU NEED TO */
		// new File(worldFile).read();
		worldTemplate = new WorldTemplate(worldFile);
		worldTemplate.load();
	}

	public static void startTicker(JPanel parent, KeyboardHandler kb) {
		ticker = new Timer(33, new Tick(parent, kb));
		ticker.start();
	}

	public static void stopTicker() {
		ticker.stop();
	}

	public static void addWall(int x, int y, int length, int direction) {
		walls.add(new Wall(x, y, length, direction));
	}

	public static Set<Wall> getWalls() {
		return walls;
	}

	public static Image getBackgroundImage() {
		return backgroundImage;
	}

	public static void setBackgroundImage(Image i) {
		backgroundImage = i;
	}

	/** World may not be instantiated. */
	private World() {
	}

}
