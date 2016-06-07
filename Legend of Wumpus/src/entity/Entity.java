package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

import display.Direction;
import display.World;

/**
 * This is the abstract Entity class. It is the superclass of pretty much
 * everything that moves.
 */
public abstract class Entity implements Serializable {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = 9080843028633986388L;
	// Variables are protected so they can be accessed by subclasses
	protected double x, y;
	/** Sprite size in PIXELS */
	protected int spriteWidth, spriteHeight;
	protected int health;
	protected Direction facing;

	// Make sure that ALL entities are registered.
	public Entity() {
		World.registerEntity(this);
	}

	/** Called every tick. AI code should go here. */
	public abstract void tick();

	/**
	 * Will be called whenever the entity collides with another entity.
	 * 
	 * @param e
	 *            the entity collided with
	 */
	public abstract void collide(Entity e);

	/**
	 * Called whenever the entity takes damage. Animations can be implemented
	 * here.
	 * 
	 * @param amount
	 *            how much damage to take
	 * @param damageSource
	 *            where the damage is coming fromF
	 */
	public abstract void damage(int amount, Entity damageSource);

	/**
	 * Set the hitbox based on imageIn. Sets spriteWidth and spriteHeight.
	 * 
	 * @param imageIn
	 *            the Image to use for hitbox calculations
	 */
	protected void setHitbox(Image imageIn) {
		this.spriteHeight = imageIn.getHeight(null);
		this.spriteWidth = imageIn.getWidth(null);
	}

	/**
	 * Called every tick to draw the entity on the screen.
	 */
	public abstract void draw(Graphics2D g);

	/** Get the x coordinate
	 * @return the x coordinate of said entity*/
	public double getX() {
		return x;
	}

	/** Get the y coordinate
	 * @return the y coordinate of said entity */
	public double getY() {
		return y;
	}

	/** Get the width in world coorinates of entity
	 * @return the width in world coorinates of said entity */
	public double getWidth() {
		return spriteWidth / 32D;
	}

	/** Get the height in world coorinates of entity
	 * @return the height in world coorinates of said entity */
	public double getHeight() {
		return spriteHeight / 32D;
	}

	/** Get the health
	 * @return the health of said entity */
	public int getHealth() {
		return health;
	}

	/** Get the direction in which the Entity is facing
	 * @return the facing of said entity */
	public Direction getFacing() {
		return facing;
	}
}
