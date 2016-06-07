package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;
import display.Direction;
import display.MusicPlayer;

/** Enemy that jumps around the screen diagonally. */
public class EntitySpider extends EntityMinion {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -4075609416053801173L;
	private static Image standingImage;
	private static Image jumpingImage;
	private static final double JUMP_DISTANCE = 3;
	private static final double SPEED = 0.1;

	private boolean jumping = false;
	private double jumpXDest = 0;

	static {
		try {
			standingImage = ImageIO
					.read(new File("assets/spider/standing.png"))
					.getScaledInstance(32, 30, Image.SCALE_REPLICATE);
			jumpingImage = ImageIO.read(new File("assets/spider/jumping.png"))
					.getScaledInstance(32, 24, Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.println("Error reading Spider images!");
			System.exit(1);
		}
	}

	public EntitySpider(double x, double y) {
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
		if (jumping) {
			spriteWidth = jumpingImage.getWidth(null);
			spriteHeight = jumpingImage.getHeight(null);
		} else {
			spriteWidth = standingImage.getWidth(null);
			spriteHeight = standingImage.getHeight(null);
		}
		if(jumping) {
			double[] nc = facing.moveInDirection(x, y, SPEED);
			x = nc[0];
			y = nc[1];
			if (Math.abs(x - jumpXDest) < 0.1)
				jumping = false;
		}
		if (Math.random() < 0.1 && !jumping)
			startJump();
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player)
			e.damage(1, this);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		try {
			MusicPlayer.playSoundEffect("assets/music/Kill.wav");
		} catch(Exception e) {
			System.out.println("Music files missing");
		}
		World.deregisterEntity(this);
	}

	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		if (jumping) {
			g.drawImage(jumpingImage, sCoords[0] - spriteWidth / 2, sCoords[1]
					- spriteHeight / 2, null);
			setHitbox(jumpingImage);
		} else {
			g.drawImage(standingImage, sCoords[0] - spriteWidth / 2, sCoords[1]
					- spriteHeight / 2, null);
			setHitbox(standingImage);
		}
	}

	private void startJump() {
		facing = whichWayToJump();
		if (!World.willCollideTile(this, JUMP_DISTANCE)) {
			jumping = true;
			double[] coords = facing.moveInDirection(x, y, JUMP_DISTANCE);
			jumpXDest = coords[0];
		}
	}

	private Direction whichWayToJump() {
		if (Math.random() < 0.5)
			if (Math.random() < 0.5)
				return Direction.SOUTHEAST;
			else
				return Direction.NORTHEAST;
		else
			if (Math.random() < 0.5)
				return Direction.SOUTHWEST;
			else
				return Direction.NORTHWEST;
	}
}