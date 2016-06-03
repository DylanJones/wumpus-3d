package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.Direction;
import display.World;

public class EntityOctorokRed extends EntityMinion {
	private static Image gremlinNorth;
	private static Image gremlinSouth;
	private static Image gremlinEast;
	private static Image gremlinWest;
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
	}

	@Override
	public void collide(Entity e) {
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		System.out.println("gremlin damage");
		health -= amount;
		if (health <= 0)
			World.deregisterEntity(this);
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
			new GremlinProjectile(this.x, this.y, 1, facing);
		}
		// TODO make random movement
		if (Math.random() < 0.01)
			randomTurn();

		if (World.willCollideTile(this, SPEED)) {
			randomTurn();
		} else {
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
	public void draw(Graphics g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		switch (facing) {
		case NORTH:
			g.drawImage(gremlinNorth, sCoords[0], sCoords[1], null);
			break;
		case SOUTH:
			g.drawImage(gremlinSouth, sCoords[0], sCoords[1], null);
			break;
		case EAST:
			g.drawImage(gremlinEast, sCoords[0], sCoords[1], null);
			break;
		case WEST:
			g.drawImage(gremlinWest, sCoords[0], sCoords[1], null);
			break;
		}
	}
}