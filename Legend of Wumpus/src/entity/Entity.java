package entity;

import java.awt.Image;

/**
 * This is the abstract Entity class. It is the superclass of all dynamic world
 * objects.
 */
public abstract class Entity {
	// Variables are protected so they can be accessed by subclasses
	protected int x;
	protected int y;
	protected Image sprite; // This is what is being drawn, not
							// a container for all sprite states.
	protected int spriteXSize;// Again, only for the current sprite
	protected int spriteYSize;
	protected double health;

	// These are things that all entities should know how to do
	/**
	 * Will be called whenever the entity collides with another entity or the
	 * side of the screen.
	 */
	public abstract void collide(Entity e);

	/**
	 * Called whenever the entity takes damage. Animations can be implemented
	 * here.
	 */
	public abstract void damage(int amount, Entity damageSource);

	/***/
	public abstract void t();

	public boolean isAlive() {
		return health > 0;
	}
}
