package display;

/** Container for information about the level's walls */
public final class Wall {
	public final int x;
	public final int y;
	public final int length;
	public final int facing;

	/**
	* Create a new Wall with the specified attributes.
	* The variables are public because they are final.
	*/
	public Wall(int x, int y, int length, int facing) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.facing = facing;
	}
}
