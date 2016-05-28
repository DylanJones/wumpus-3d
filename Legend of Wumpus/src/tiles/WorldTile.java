package tiles;

import java.awt.Graphics;

public interface WorldTile {
	/**
	 * Get the numeric ID of the tile type. Used for world loading/saving.
	 * 
	 * @return the numeric tile ID
	 */
	public int getCode();

	/**
	 * Find if a tile is able to be walked on or not.
	 * 
	 * @return if the tile can be walked on
	 */
	public boolean isSolid();

	/**
	 * Draw the tile at the specified coordinates. These are the int coords, not
	 * float.
	 * 
	 * @param x
	 *            the x coordinate to draw at
	 * @param y
	 *            the y coordinate to draw at
	 * @param g
	 *            the Graphics object to draw on
	 */
	public void draw(int x, int y, Graphics g);

	/**
	 * Perform the action for when a tile is interacted with. Player can
	 */
	// public void interact(Entity e);
}