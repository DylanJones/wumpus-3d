package entity;

import javax.imageio.ImageIO;

import display.World;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class should handle all of the things related to the Player. It should
 * NOT handle drawing the GUI (beyond drawing the player sprite).
 */
@SuppressWarnings("unused")
public final class Player extends Entity {
	private static Image northImage;
	private static Image southImage;
	private static Image eastImage;
	private static Image westImage;
	private int facing = World.NORTH;
	private long lastAttackTime = 0;

	static {
		try {
			northImage = ImageIO.read(new File("assets/wumpus/north.png"));
			southImage = ImageIO.read(new File("assets/wumpus/north.png"));
			eastImage = ImageIO.read(new File("assets/wumpus/north.png"));
			westImage = ImageIO.read(new File("assets/wumpus/north.png"));
		} catch (IOException e) {
		}
	}

	public Player() {
		this.health = 100;
		this.x = 100;
		this.y = 100;
	}

	public void draw(Graphics g) {
		switch (facing) {
		case World.NORTH:
			g.drawImage(northImage, getX(), getY(), null);
			break;
		case World.SOUTH:
			g.drawImage(southImage, getX(), getY(), null);
			break;
		case World.EAST:
			g.drawImage(eastImage, getX(), getY(), null);
			break;
		case World.WEST:
			g.drawImage(westImage, getX(), getY(), null);
			break;
		}
	}

	@Override
	public void collide(Entity e) {
//		System.out.println("Player Collision");
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		if (System.currentTimeMillis() - this.lastAttackTime > 1000) {
			this.lastAttackTime = System.currentTimeMillis();
			health -= amount;
			System.out.println("Player damaged! Health: " + health);
		}
	}
}
