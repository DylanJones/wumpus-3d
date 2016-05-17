package entity;

import java.awt.Graphics;
import java.awt.Image;

import display.World;

/**
 * This is the abstract Entity class. It is the superclass of pretty much
 * everything that moves.
 */
public abstract class Entity {
	// Variables are protected so they can be accessed by subclasses
	protected int x, y;
	protected int spriteWidth, spriteHeight; // the size of the sprite
	protected int health;

	// Make sure that ALL entities are registered.
	public Entity() {
		World.registerEntity(this);
	}

	/** Called every tick. AI code should go here. */
	public abstract void tick();

	/**
	 * Will be called whenever the entity collides with another entity.
	 */
	public abstract void collide(Entity e);

	/**
	 * Called whenever the entity takes damage. Animations can be implemented
	 * here.
	 */
	public abstract void damage(int amount, Entity damageSource);

	/**
	 * Called every tick to draw the entity on the screen.
	 */
	public abstract void draw(Graphics g);
	/**
	* Retrieving entity data
	*/
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return spriteWidth;
	}

	public int getHeight() {
		return spriteHeight;
	}
}
