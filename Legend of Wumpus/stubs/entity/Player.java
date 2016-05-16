package entity;

import javax.imageio.ImageIO;

import display.Wall;
import display.World;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * This class should handle all of the things related to the Player. It should
 * NOT handle drawing the GUI (beyond drawing the player sprite).
 */
public final class Player extends Entity {
	private static final int ATTACK_COOLDOWN = 500; // attack cooldown in millis
	private static Image northImage;
	private static Image southImage;
	private static Image eastImage;
	private static Image westImage;
	private static Image northAttackImage;
	private static Image southAttackImage;
	private static Image eastAttackImage;
	private static Image westAttackImage;

	private int facing = World.NORTH;
	private long lastDamageTime = 0;
	private long attackStartTime = 0;

	static {
		//REad player image files
	}

	public Player() {
		//Start at 300,100 with 10 health
		this.health = 10;
		this.x = 300;
		this.y = 100;
	}

	/**
	 * @param g
	 *            the graphics object to graw on
	 */
	@Override
	public void draw(Graphics g) {
		//Draw the correct image at x,y based on facing and attack state
	}

	@Override
	public void collide(Entity e) {
		//If we're attacking: damage the entity
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		//If we are not attacking and health is greater than 0: damage the player
	}

	public void turnLeft() {
		//Increment facing
	}

	public int getDirection() {
		return facing;
	}

	public void tick() {
		//Set the correct sprite width and hieght
	}

	public void move(int pixels) {
		//Move in the current direcrtion by the specified number of pixels
	}

	public void attack() {
		//Set attackStartTime to System.currentTimeMillis()
	}

	public int getHealth() {
		return health;
	}
}