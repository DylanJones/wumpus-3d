package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import display.Direction;
import display.World;

/**
 * This is the abstract Entity class. It is the superclass of pretty much
 * everything that moves.
 */
public abstract class Entity {
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
	 */
	public abstract void collide(Entity e);

	/**
	 * Called whenever the entity takes damage. Animations can be implemented
	 * here.
	 */
	public abstract void damage(int amount, Entity damageSource);
	
	protected void setHitbox(Image imageIn) {
		this.spriteHeight = imageIn.getHeight(null);
		this.spriteWidth = imageIn.getWidth(null);
	}

	/**
	 * Called every tick to draw the entity on the screen.
	 */
	public abstract void draw(Graphics2D g);

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return spriteWidth / 32D;
	}

	public double getHeight() {
		return spriteHeight / 32D;
	}

	public int getHealth() {
		return health;
	}

	public Direction getFacing() {
		return facing;
	}
}
