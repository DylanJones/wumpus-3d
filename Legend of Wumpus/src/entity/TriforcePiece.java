package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

/** The triforce piece dropped by bosses */
public class TriforcePiece extends EntityItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8053647258394340726L;
	private static Image myImage;

	static {
		try {
			myImage = ImageIO
					.read(new File("assets/items/triforce_orange.png"));
		} catch (IOException e) {
			System.err.println("Error reading Triforce image files! ");
			System.exit(1);
		}
	}

	public TriforcePiece(double x, double y) {
		this.x = x;
		this.y = y;
		this.setHitbox(myImage);
	}

	@Override
	public void tick() {
		// Nothing to do here
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			World.deregisterEntity(this);
			((Player) e).addTriforce();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteWidth / 2;
		sCoords[1] -= spriteHeight / 2;
		g.drawImage(myImage, sCoords[0], sCoords[1], null);
	}

}
