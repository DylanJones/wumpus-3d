package display;

import java.util.ArrayList;
import entity.Entity;

/** This is a class of constants. It will contain global variables. */
public final class World {
	public ArrayList<Entity> curretEntities = new ArrayList<Entity>();
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
}
