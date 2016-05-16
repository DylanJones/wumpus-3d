package display;

import javax.swing.Timer;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;

/** This is a class of constants. It will contain global variables. */
public final class World {
	// Game state: 0 = overworld, 1 = bossfight, 2 = dead, 3 = dungeon
	// 4 = title screen / loading
	private static int gameState = 0;
	private static Image backgroundImage;
	private static HashSet<Entity> entities = new HashSet<Entity>();
	private static HashSet<Wall> walls = new HashSet<Wall>();
	private static Player thePlayer;
	private static Timer ticker;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

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
	 * @param direction Which direction to load the world from
	 */
	public static void loadWorld(int direction) {
	}

	/**
	* Load the specified world file into memory, and kill all the previouisus things that were in the world
	* @param worldFile The world file to load
	*/
	public static void loadWorld(String worldFile) {
		WorldTemplate worldTemplate = new WorldTemplate(worldFile);
		worldTemplate.load();
	}

	/**
	* Start the world clock with the parent and keyboardhandler
	* @param parent  The parent of the new tick
	* @param kb  The keyboardhandler associated with parent
	*/
	public static void startTicker(JPanel parent, KeyboardHandler kb) {
		ticker = new Timer(33, new Tick(parent, kb));
		ticker.start();
	}

	/**
	* Stop the world clock.
	*/
	public static void stopTicker() {
		ticker.stop();
	}

	/**
	* Add a wall to the world.
	* @param x The x coordinate of the wall
	* @param y The y coordinate of the wall
	* @param length The length of the wall
	* @param direction Is the wall horizontal or vertical
	*/
	public static void addWall(int x, int y, int length, int direction) {
		walls.add(new Wall(x, y, length, direction));
	}

	/**
	* Return a set view of all the walls.
	* @return the walls
	*/
	public static Set<Wall> getWalls() {
		return new HashSet<Wall>(walls);
	}
	
	/**
	* Checks for a collison in the specifid movment
	* @param x the x coordinate of entity
	* @param y the y coordinate of entity
	* @param facing The direction in which to move
	* @param movment The amount that will be moved in pixels
	* @return Whetehr or not the movment will run into a wall
	*/
	public static boolean willCollide(int x, int y, int facing, int movment) {
	}

	/**
	* Get the background image.
	* @return the background image for the current screen
	*/
	public static Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	* Load and set the backgriund image for the wolrd
	* @param filename The filename of the new background image
	* 
	*/
	public static void setBackgroundImage(String filename) {
	}

	/** World may not be instantiated. */
	private World() {
	}

}
