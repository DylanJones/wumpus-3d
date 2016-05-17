package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntitySpider extends EntityMinion {
	private enum Direction {
		NORTHWEST, SOUTHWEST, SOUTHEAST, NORTHEAST;
	}
	private static Image standingImage;
	private static Image jumpingImage;

	private boolean jumping = false;
	private long lastJumpTime = 0;
	private int jumpXDest = 0;
	private int jumpYDest = 0;

	static {
		try {
			standingImage = ImageIO.read(new File("assets/spider/standing.png"));
			jumpingImage = ImageIO.read(new File("assets/spider/jumping.png"));
		} catch (IOException e) {
			System.err.println("Error reading Spider images!");
			System.exit(1);
		}
	}

	public EntitySpider(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		World.deregisterEntity(this);
	}

	@Override
	public void draw(Graphics g) {
		if (jumping) {
			g.drawImage(jumpingImage, x + spriteWidth / 2, y + spriteHeight / 2, null);
		} else {
			g.drawImage(standingImage, x + spriteWidth / 2, y + spriteHeight / 2, null);
		}
	}

	private Direction whichWayToJump() {
		int playerX = World.getThePlayer().getX();
		int playerY = World.getThePlayer().getY();
		if(playerX > x) {
			if(playerY > y)
				return Direction.SOUTHEAST;
			else
				return Direction.NORTHEAST;
		} else {
			if(playerY > y)
				return Direction.SOUTHWEST;
			else
				return Direction.NORTHWEST;
		}
	}
}