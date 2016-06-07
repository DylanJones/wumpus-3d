package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.Direction;
import display.World;
import display.MusicPlayer;

/** Red enemies that walk around randomly and shoot red projectiles. */
public class EntityOctorokRed extends EntityMinion {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -5085421657242795653L;
	private static Image gremlinNorth;
	private static Image gremlinSouth;
	private static Image gremlinEast;
	private static Image gremlinWest;
	private static Image projectileImage;
	private static final double SPEED = 0.1;

	static {
		try {
			gremlinNorth = ImageIO.read(
					new File("assets/gremlin/gremlin-north.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			gremlinSouth = ImageIO.read(
					new File("assets/gremlin/gremlin-south.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			gremlinEast = ImageIO.read(
					new File("assets/gremlin/gremlin-east.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			gremlinWest = ImageIO.read(
					new File("assets/gremlin/gremlin-west.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			projectileImage = ImageIO.read(new File(
					"assets/gremlin/projectile.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading Gremlin images");
			System.exit(1);
		}
	}

	public EntityOctorokRed(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.spriteHeight = gremlinNorth.getHeight(null);
		this.spriteWidth = gremlinNorth.getWidth(null);
		this.health = 1;
		this.facing = Direction.NORTH;
		setHitbox(gremlinNorth);
	}

	@Override
	public void collide(Entity e) {
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		health -= amount;
		if (health <= 0) {
			try {
				MusicPlayer.playSoundEffect("assets/music/Kill.wav");
			} catch(Exception e) {
				System.out.println("Music files missing");
			}
			World.deregisterEntity(this);
			if (Math.random() < 0.5) {
				new EntityHeart(x, y, 2);
			}
		} else {
			try {
				MusicPlayer.playSoundEffect("assets/music/Hit.wav");
			} catch(Exception e) {
				System.out.println("Music files missing");
			}
		}
	}

	@Override
	public void attack(Entity target) {
		if (target instanceof Player)
			target.damage(1, this);
	}

	@Override
	public void tick() {
		// Shoot player
		if (Math.random() < 0.03) {
			new EntityProjectile(this.x, this.y, 1, facing, projectileImage);
		}
		// Randomly turn
		if (Math.random() < 0.01)
			randomTurn();
		// Move
		if (World.willCollideTile(this, SPEED)) {
			randomTurn();
		} else { // Turn
			double[] newCoords = facing.moveInDirection(x, y, SPEED);
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	private void randomTurn() {
		if (Math.random() < 0.5) {
			facing = facing.getLeft();
		} else {
			facing = facing.getRight();
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] = sCoords[0] - this.spriteWidth / 2;
		sCoords[1] = sCoords[1] - this.spriteHeight / 2;
		switch (facing) {
		case NORTH:
			g.drawImage(gremlinNorth, sCoords[0], sCoords[1], null);
			setHitbox(gremlinNorth);
			break;
		case SOUTH:
			g.drawImage(gremlinSouth, sCoords[0], sCoords[1], null);
			setHitbox(gremlinSouth);
			break;
		case EAST:
			g.drawImage(gremlinEast, sCoords[0], sCoords[1], null);
			setHitbox(gremlinEast);
			break;
		case WEST:
			g.drawImage(gremlinWest, sCoords[0], sCoords[1], null);
			setHitbox(gremlinWest);
			break;
		}
	}
}