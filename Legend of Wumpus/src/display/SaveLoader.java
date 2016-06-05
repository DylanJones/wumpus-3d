package display;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import entity.Entity;

public class SaveLoader {
	private static Map<String, Set<Entity>> worlds = new HashMap<String, Set<Entity>>();

	public static boolean hasVisited(String worldName) {
		return worlds.get(worldName) != null;
	}

	public static void onWorldLeave() {
		Set<Entity> entities = World.getAllEntities();
		entities.remove(World.getThePlayer());
		worlds.put(World.getWorldName(), entities);
	}
	
	public static void loadEntities (String worldName) {
		Set<Entity> entities = worlds.get(worldName);
		entities.add(World.getThePlayer());
		for (Entity e : entities) {
			World.registerEntity(e);
		}
	}
}