package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.Direction;
import display.World;

public class GremlinProjectile extends EntityItem {
	protected static Image projectileImage;
	private static final int SPEED = 7;
	private int damageAmount;
	private Direction facing;

	static {
		try {
			projectileImage = ImageIO.read(new File("assets/items/projectile.png"));
		} catch (IOException e) {
			System.err.println("Error reading projectile images");
			System.exit(1);
		}
	}

	public GremlinProjectile(int x, int y, int damageAmount, Direction facing2) {
		super();
		this.x = x;
		this.y = y;
		this.damageAmount = damageAmount;
		this.facing = facing2;
		this.spriteHeight = projectileImage.getHeight(null);
		this.spriteWidth = projectileImage.getWidth(null);
	}

	@Override
	public void tick() {
		// Die if we fall off the screen
		if (this.x > 512 || this.x < 0 || this.y > 384 || this.y < 0)
			World.deregisterEntity(this);
		switch (facing) {
		case NORTH:
			this.y -= SPEED;
			break;
		case SOUTH:
			this.y += SPEED;
			break;
		case EAST:
			this.x += SPEED;
			break;
		case WEST:
			this.x -= SPEED;
			break;
		}
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			e.damage(damageAmount, this);
			World.deregisterEntity(this);
		}
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		// Do nothing; projectiles are immune to damage
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(projectileImage, x, y, null);
	}

}
