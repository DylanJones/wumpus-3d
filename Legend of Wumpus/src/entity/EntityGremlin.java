package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntityGremlin extends EntityMinion {
	private static Image gremlinNorth;
	private static Image gremlinSouth;
	private static Image gremlinEast;
	private static Image gremlinWest;
	private int facing = World.NORTH;
	// For its walking square
	private int squareX1 = 0;
	private int squareY1 = 0;
	private int squareX2 = 0;
	private int squareY2 = 0;
	private boolean clockwise;

	static {
		try {
			gremlinNorth = ImageIO.read(new File("assets/gremlin/gremlin-north.png")).getScaledInstance(32, 32,
					Image.SCALE_FAST);
			gremlinSouth = ImageIO.read(new File("assets/gremlin/gremlin-south.png")).getScaledInstance(32, 32,
					Image.SCALE_FAST);
			gremlinEast = ImageIO.read(new File("assets/gremlin/gremlin-east.png")).getScaledInstance(32, 32,
					Image.SCALE_FAST);
			gremlinWest = ImageIO.read(new File("assets/gremlin/gremlin-west.png")).getScaledInstance(32, 32,
					Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading Gremlin images");
			System.exit(1);
		}
	}

	// Gremlins walk in squares
	public EntityGremlin(int squareX1, int squareY1, int squareX2, int squareY2) {
		super();
		this.x = squareX1;
		this.y = squareY1;
		this.squareX1 = squareX1;
		this.squareY1 = squareY1;
		this.squareX2 = squareX2;
		this.squareY2 = squareY2;
		this.spriteHeight = gremlinNorth.getHeight(null);
		this.spriteWidth = gremlinNorth.getWidth(null);
		this.health = 1;
		this.clockwise = Math.random() > 0.5;
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
			new EntityProjectile(this.x, this.y, 1, facing, this);
		}

		// Walk along the square
		if (this.clockwise) {
			switch (facing) {
			case World.EAST:
				if (this.x == squareX2) {
					this.facing = World.SOUTH;
				} else {
					this.x += 1;
				}
				break;
			case World.SOUTH:
				if (this.y == squareY2) {
					this.facing = World.WEST;
				} else {
					this.y += 1;
				}
				break;
			case World.WEST:
				if (this.x == squareX1) {
					this.facing = World.NORTH;
				} else {
					this.x -= 1;
				}
				break;
			case World.NORTH:
				if (this.y == squareY1) {
					this.facing = World.EAST;
				} else {
					this.y -= 1;
				}
				break;
			}
		} else {
			switch (facing) {
			case World.EAST:
				if (this.x == squareX2) {
					this.facing = World.NORTH;
				} else {
					this.x += 1;
				}
				break;
			case World.SOUTH:
				if (this.y == squareY2) {
					this.facing = World.EAST;
				} else {
					this.y += 1;
				}
				break;
			case World.WEST:
				if (this.x == squareX1) {
					this.facing = World.SOUTH;
				} else {
					this.x -= 1;
				}
				break;
			case World.NORTH:
				if (this.y == squareY1) {
					this.facing = World.WEST;
				} else {
					this.y -= 1;
				}
				break;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		switch (facing) {
		case World.NORTH:
			g.drawImage(gremlinNorth, x - this.getWidth() / 2, y - this.getHeight() / 2, null);
			break;
		case World.SOUTH:
			g.drawImage(gremlinSouth, x - this.getWidth() / 2, y - this.getHeight() / 2, null);
			break;
		case World.EAST:
			g.drawImage(gremlinEast, x - this.getWidth() / 2, y - this.getHeight() / 2, null);
			break;
		case World.WEST:
			g.drawImage(gremlinWest, x - this.getWidth() / 2, y - this.getHeight() / 2, null);
			break;
		}
	}
}