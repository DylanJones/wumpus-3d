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
	private static Image northImage;
	private static Image southImage;
	private static Image eastImage;
	private static Image westImage;

	private int facing = World.NORTH;
	private long lastDamageTime = 0;

	static {
		try {
			northImage = ImageIO.read(new File("assets/wumpus/north.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			southImage = ImageIO.read(new File("assets/wumpus/south.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			eastImage = ImageIO.read(new File("assets/wumpus/east.png")).getScaledInstance(28, 30,
					Image.SCALE_REPLICATE);
			westImage = ImageIO.read(new File("assets/wumpus/west.png")).getScaledInstance(28, 30,
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
		this.x = 300;
		this.y = 100;
	}

	/**
	 * @param g
	 *            the graphics object to graw on
	 */
	@Override
	public void draw(Graphics g) {
		switch (facing) {
		case World.NORTH:
			g.drawImage(northImage, getX() - this.getWidth() / 2, getY() - this.getHeight() / 2, null);
			break;
		case World.SOUTH:
			g.drawImage(southImage, getX() - this.getWidth() / 2, getY() - this.getHeight() / 2, null);
			break;
		case World.EAST:
			g.drawImage(eastImage, getX() - this.getWidth() / 2, getY() - this.getHeight() / 2, null);
			break;
		case World.WEST:
			g.drawImage(westImage, getX() - this.getWidth() / 2, getY() - this.getHeight() / 2, null);
			break;
		}
	}

	@Override
	public void collide(Entity e) {
		// System.out.println("Player Collision");
		// e.damage(1, this);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		if (System.currentTimeMillis() - lastDamageTime > 1000) {
			lastDamageTime = System.currentTimeMillis();
			if (health > 0) {// Stops player from having negative health
				health -= amount;
				if (health <= 0) // did it go below 0?
					World.setGameState(2);
			}
			System.out.println("Player damaged! Health: " + health);
		}
	}

	public void turnLeft() {
		facing = facing >= 3 ? 0 : facing + 1;
	}

	public int getDirection() {
		return facing;
	}

	public void tick() {
	}

	/**
	 * east / west: player.x +- movment < Math.abs(player.x +- movment - wall.x)
	 * + wall.x && player.x +- movment > wall.x north / south: player.y +-
	 * movment < Math.abs(player.y +- movment - wall.y) + wall.y && player.y +-
	 * movment > wall.y
	 */
	public void move(int pixels) {
		boolean canMove = !World.willCollide(x, y, facing, pixels);
		if (canMove) {
			switch (facing) {
			case World.NORTH:
				if (y >= pixels) {
					y -= pixels;
				} else {
					y = 480;
					World.loadWorld(World.NORTH);
				}
				break;
			case World.SOUTH:
				if (y <= 480 - pixels) {
					y += pixels;
				} else {
					y = 0;
					World.loadWorld(World.SOUTH);
				}
				break;
			case World.EAST:
				if (x <= 640 - pixels) {
					x += pixels;
				} else {
					x = 0;
					World.loadWorld(World.EAST);
				}
				break;
			case World.WEST:
				if (x >= pixels) {
					x -= pixels;
				} else {
					x = 640;
					World.loadWorld(World.WEST);
				}
				break;
			}
		}
	}

	public void attack() {
	}

	public int getHealth() {
		return health;
	}
}
