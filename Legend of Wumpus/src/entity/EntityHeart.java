package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;

import display.World;
import display.MusicPlayer;

/** The thing dropped by enemies that restores your health. */
public class EntityHeart extends EntityItem {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -808919570444662375L;
	private static Image heartImage;
	private int healAmount;

	static {
		try {
			heartImage = ImageIO.read(
					EntityHeart.class
							.getResource("/assets/items/heart_canister.png"))
					.getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			;
		} catch (Exception e) {
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
			MusicPlayer.playSoundEffect("/assets/music/Get_Heart.wav");
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
