package entity;

import java.awt.Graphics;
import java.awt.Image;

import display.World;

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
	protected int health;

	// Make sure that ALL entities are registered.
	public Entity() {
		World.registerEntity(this);
	}

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

	/**
	 * Called every tick to draw the entity's sprite. All methods that override
	 * this should have a call to super.draw()
	 */
	public void draw(Graphics g) {
		g.drawImage(sprite, getX(), getY(), null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	public boolean isAlive() {
		return health > 0;
	}
}
