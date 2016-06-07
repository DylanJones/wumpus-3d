package entity;

import java.awt.Graphics2D;
import java.awt.Image;
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
	private static Image octorokNorth;
	private static Image octorokSouth;
	private static Image octorokEast;
	private static Image octorokWest;
	private static Image projectileImage;
	private static final double SPEED = 0.1;

	static {
		try {
			octorokNorth = ImageIO.read(
					EntityOctorokRed.class
							.getResource("/assets/gremlin/gremlin-north.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			octorokSouth = ImageIO.read(
					EntityOctorokRed.class
							.getResource("/assets/gremlin/gremlin-south.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			octorokEast = ImageIO.read(
					EntityOctorokRed.class
							.getResource("/assets/gremlin/gremlin-east.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			octorokWest = ImageIO.read(
					EntityOctorokRed.class
							.getResource("/assets/gremlin/gremlin-west.png"))
					.getScaledInstance(32, 32, Image.SCALE_FAST);
			projectileImage = ImageIO.read(EntityOctorokRed.class
					.getResource("/assets/gremlin/projectile.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error reading Gremlin images");
			System.exit(1);
		}
	}

	public EntityOctorokRed(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.spriteHeight = octorokNorth.getHeight(null);
		this.spriteWidth = octorokNorth.getWidth(null);
		this.health = 1;
		this.facing = Direction.NORTH;
		setHitbox(octorokNorth);
	}

	@Override
	public void collide(Entity e) {
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		health -= amount;
		if (health <= 0) {
			MusicPlayer.playSoundEffect("/assets/music/Kill.wav");
			World.deregisterEntity(this);
			if (Math.random() < 0.5) {
				new EntityHeart(x, y, 2);
			}
		} else {
			MusicPlayer.playSoundEffect("/assets/music/Hit.wav");
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
			g.drawImage(octorokNorth, sCoords[0], sCoords[1], null);
			setHitbox(octorokNorth);
			break;
		case SOUTH:
			g.drawImage(octorokSouth, sCoords[0], sCoords[1], null);
			setHitbox(octorokSouth);
			break;
		case EAST:
			g.drawImage(octorokEast, sCoords[0], sCoords[1], null);
			setHitbox(octorokEast);
			break;
		case WEST:
			g.drawImage(octorokWest, sCoords[0], sCoords[1], null);
			setHitbox(octorokWest);
			break;
		}
	}
}