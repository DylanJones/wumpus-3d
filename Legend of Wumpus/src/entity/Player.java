package entity;

import javax.imageio.ImageIO;

import display.Direction;
import display.World;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * This class should handle all of the things re
 * lated to the Player. It should
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

	private Direction facing = Direction.NORTH;
	private long lastDamageTime = 0;
	private long attackStartTime = 0;

	static {
		try {
			// Walking images
			northImage = ImageIO.read(new File("assets/wumpus/north.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			southImage = ImageIO.read(new File("assets/wumpus/south.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			eastImage = ImageIO.read(new File("assets/wumpus/east.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			westImage = ImageIO.read(new File("assets/wumpus/west.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			// Attacking images
			northAttackImage = ImageIO.read(new File("assets/wumpus/attack_north.png")).getScaledInstance(32, 56,
					Image.SCALE_REPLICATE);
			southAttackImage = ImageIO.read(new File("assets/wumpus/attack_south.png")).getScaledInstance(32, 54,
					Image.SCALE_REPLICATE);
			eastAttackImage = ImageIO.read(new File("assets/wumpus/attack_east.png")).getScaledInstance(54, 30,
					Image.SCALE_REPLICATE);
			westAttackImage = ImageIO.read(new File("assets/wumpus/attack_west.png")).getScaledInstance(54, 30,
					Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.print("Error reading Player image files");
			System.exit(1);
		}
	}

	public Player() {
		this.health = 10;
		this.spriteHeight = northImage.getHeight(null);
		this.spriteWidth = northImage.getWidth(null);
		this.x = 2;
		this.y = 2;
	}

	/**
	 * @param g
	 *            the graphics object to graw on
	 */
	@Override
	public void draw(Graphics g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		switch (facing) {
		case NORTH:
			if (System.currentTimeMillis() - this.attackStartTime < 500)
				g.drawImage(northAttackImage, sCoords[0], sCoords[1], null);
			else
				g.drawImage(northImage, sCoords[0], sCoords[1], null);
			break;
		case SOUTH:
			if (System.currentTimeMillis() - this.attackStartTime < 500)
				g.drawImage(southAttackImage, sCoords[0], sCoords[1], null);
			else
				g.drawImage(southImage, sCoords[0], sCoords[1], null);
			break;
		case EAST:
			if (System.currentTimeMillis() - this.attackStartTime < 500)
				g.drawImage(eastAttackImage, sCoords[0], sCoords[1], null);
			else
				g.drawImage(eastImage, sCoords[0], sCoords[1], null);
			break;
		case WEST:
			if (System.currentTimeMillis() - this.attackStartTime < 500)
				g.drawImage(westAttackImage, sCoords[0], sCoords[1], null);
			else
				g.drawImage(westImage, sCoords[0], sCoords[1], null);
			break;
		}
	}

	@Override
	public void collide(Entity e) {
		if (System.currentTimeMillis() - this.attackStartTime < ATTACK_COOLDOWN)
			e.damage(2, this);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		if (System.currentTimeMillis() - lastDamageTime > 1000) {
			lastDamageTime = System.currentTimeMillis();
			// We're invulrnaberale while attacking
			if (System.currentTimeMillis() - this.attackStartTime > ATTACK_COOLDOWN) {
				if (health > 0) {// Stops player from having negative health
					health -= amount;
					if (health <= 0) // did it go below 0?
						World.setGameState(2);
				}
				System.out.println("Player damaged! Health: " + health);
			}
		}
	}

	public void turnLeft() {
		facing = facing.getLeft();
	}

	public void tick() {
		if (System.currentTimeMillis() - attackStartTime < ATTACK_COOLDOWN) {
			switch (facing) {
			case NORTH:
				spriteHeight = northAttackImage.getHeight(null);
				spriteWidth = northAttackImage.getWidth(null);
				break;
			case SOUTH:
				spriteHeight = southAttackImage.getHeight(null);
				spriteWidth = southAttackImage.getWidth(null);
				break;
			case EAST:
				spriteHeight = eastAttackImage.getHeight(null);
				spriteWidth = eastAttackImage.getWidth(null);
				break;
			case WEST:
				spriteHeight = westAttackImage.getHeight(null);
				spriteWidth = westAttackImage.getWidth(null);
				break;
			}
		} else {
			switch (facing) {
			case NORTH:
				spriteHeight = northImage.getHeight(null);
				spriteWidth = northImage.getWidth(null);
				break;
			case SOUTH:
				spriteHeight = southImage.getHeight(null);
				spriteWidth = southImage.getWidth(null);
				break;
			case EAST:
				spriteHeight = eastImage.getHeight(null);
				spriteWidth = eastImage.getWidth(null);
				break;
			case WEST:
				spriteHeight = westImage.getHeight(null);
				spriteWidth = westImage.getWidth(null);
				break;
			}
		}
	}

	public void move(double amount) {
		boolean canMove = !World.willCollide(this, amount);
		// Can't move while attacking
		if (System.currentTimeMillis() - this.attackStartTime < ATTACK_COOLDOWN)
			canMove = false;
		//Move in specified direction
		if (canMove) {
			switch (facing) {
			case NORTH:
				// IF we are not walking off the screen:
				if (y >= amount) {
					y -= amount;
				} else {
					y = 11.9;
					World.loadWorld(Direction.NORTH);
				}
				break;
			case SOUTH:
				if (y <= 11.9 - amount) {
					y += amount;
				} else {
					y = 0;
					World.loadWorld(Direction.SOUTH);
				}
				break;
			case EAST:
				if (x <= 16 - amount) {
					x += amount;
				} else {
					x = 0;
					World.loadWorld(Direction.EAST);
				}
				break;
			case WEST:
				if (x >= amount) {
					x -= amount;
				} else {
					x = 16;
					World.loadWorld(Direction.WEST);
				}
				break;
			}
		}
	}

	public void attack() {
		if (System.currentTimeMillis() - this.attackStartTime > ATTACK_COOLDOWN)
			attackStartTime = System.currentTimeMillis();
	}
}