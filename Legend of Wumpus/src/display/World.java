package display;

import java.util.*;
import entity.Entity;
import entity.Player;

/** This is a class of constants. It will contain global variables. */
public final class World {
	// Game state: 0 = overworld, 1 = boss, 2 = title screen
	private static int gameState = 0;
	private static HashSet<Entity> entities = new HashSet<Entity>();
	private static Player thePlayer;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	static {
		thePlayer = new Player();
	}

	public static void registerEntity(Entity e) {
		entities.add(e);
	}
	
	public static void deregisterEntity(Entity e) {
		entities.remove(e);
	}

	public static Set<Entity> getAllEntities() {
		return entities;
	}

	/**
	 * @return the gameState
	 */
	public static int getGameState() {
		return gameState;
	}

	/**
	 * @param gameState
	 *            the gameState to set
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
}
