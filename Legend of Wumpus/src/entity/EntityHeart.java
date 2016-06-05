package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntityHeart extends EntityItem {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -808919570444662375L;
	private static Image heartImage;
	private int healAmount;
	
	static {
		try {
			heartImage = ImageIO.read(new File("assets/items/heart_canister.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading Heart images!");
			System.exit(1);
		}
	}

	public EntityHeart(double x, double y) {
		this(x, y, 1);
	}
	
	public EntityHeart(double x, double y, int healAmount) {
		super();
		this.setHitbox(heartImage);
		this.x = x;
		this.y = y;
		this.healAmount = healAmount;
	}

	@Override
	public void tick() {
		// Do nothing; we just sit here
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			((Player) e).heal(healAmount);
			World.deregisterEntity(this);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteHeight / 2;
		sCoords[1] -= spriteWidth / 2;
		g.drawImage(heartImage, sCoords[0], sCoords[1], null);
	}
}
