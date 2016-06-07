package display;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import entity.Entity;

/**
 * Although not fully implemented, this class will save and load games from the
 * disk.
 */
public class SaveLoader {
	private static Map<String, Set<Entity>> worlds = new HashMap<String, Set<Entity>>();

	/**
	 * Check whether or not a map location has been visited before.
	 * 
	 * @param worldName
	 *            the world to check
	 * @return whether or not the world has been visited
	 */
	public static boolean hasVisited(String worldName) {
		return worlds.get(worldName) != null;
	}

	/** World calls this to save the list of entities as it leaves the world. */
	public static void onWorldLeave() {
		Set<Entity> entities = World.getAllEntities();
		entities.remove(World.getThePlayer());
		worlds.put(World.getWorldName(), entities);
	}

	/**
	 * World calls this method to load entities from a previously visited world
	 * back into memory.
	 */
	public static void loadEntities(String worldName) {
		Set<Entity> entities = worlds.get(worldName);
		entities.add(World.getThePlayer());
		for (Entity e : entities) {
			World.registerEntity(e);
		}
	}

}