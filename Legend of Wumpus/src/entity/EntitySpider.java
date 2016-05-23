package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntitySpider extends EntityMinion {
	// Enum because WHY NOT.
	private enum Direction {
		NORTHWEST {
			public final int nextXAdd = JUMP_DISTANCE * -1;
			public final int nextYAdd = JUMP_DISTANCE;
		}, SOUTHWEST {
			public final int nextXAdd = JUMP_DISTANCE;
			public final int nextYAdd = JUMP_DISTANCE;
		}, SOUTHEAST {
			public final int nextXAdd = JUMP_DISTANCE;
			public final int nextYAdd = JUMP_DISTANCE * -1;
		}, NORTHEAST {
			public final int nextXAdd = JUMP_DISTANCE * -1;
			public final int nextYAdd = JUMP_DISTANCE * -1;
		};
		public final int nextXAdd = 0;
		public final int nextYAdd = 0;
	}

	private static Image standingImage;
	private static Image jumpingImage;
	private static final int JUMP_DISTANCE = 50;

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

	private void startJump() {
		Direction d = whichWayToJump();
		if (canJump(d)) {
			jumping = true;
			lastJumpTime = System.currentTimeMillis();
		}
	}

	private boolean canJump(Direction dir) {
		switch (dir) {
		case NORTHEAST:
			if (World.willCollide(x, y, World.NORTH, JUMP_DISTANCE))
				if (World.willCollide(x, y - JUMP_DISTANCE, World.EAST, JUMP_DISTANCE))
					return true;
			break;
		case NORTHWEST:
			if (World.willCollide(x, y, World.NORTH, JUMP_DISTANCE))
				if (World.willCollide(x, y - JUMP_DISTANCE, World.WEST, JUMP_DISTANCE))
					return true;
			break;
		case SOUTHEAST:
			if (World.willCollide(x, y, World.NORTH, JUMP_DISTANCE))
				if (World.willCollide(x, y + JUMP_DISTANCE, World.EAST, JUMP_DISTANCE))
					return true;
			break;
		case SOUTHWEST:
			if (World.willCollide(x, y, World.NORTH, JUMP_DISTANCE))
				if (World.willCollide(x, y + JUMP_DISTANCE, World.WEST, JUMP_DISTANCE))
					return true;
			break;
		}
		return false;
	}

	private Direction whichWayToJump() {
		int playerX = World.getThePlayer().getX();
		int playerY = World.getThePlayer().getY();
		if (playerX > x) {
			if (playerY > y)
				return Direction.SOUTHEAST;
			else
				return Direction.NORTHEAST;
		} else {
			if (playerY > y)
				return Direction.SOUTHWEST;
			else
				return Direction.NORTHWEST;
		}
	}
}