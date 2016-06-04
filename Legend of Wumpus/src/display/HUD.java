package display;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class HUD {
	private static Image heart = null;
	private static Image half_heart = null;
	private static Image empty_heart = null;

	static {
		try {
			heart = ImageIO.read(new File("assets/items/heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			half_heart = ImageIO.read(new File("assets/items/half_heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			empty_heart = ImageIO.read(new File("assets/items/empty_heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.print("Error loading files for HUD");
			System.exit(1);
		}
	}

	/**
	 * Draws the health bar and anything else related to health. (e.g. amount)
	 */
	private static void drawHealth(Graphics g) {
		int full_hearts = World.getThePlayer().getMaxHealth()/2;
		boolean hasHalf = false;

		if(World.getThePlayer().getMaxHealth() > World.getThePlayer().getHealth())
			full_hearts = (World.getThePlayer().getHealth()/2);

		if(World.getThePlayer().getHealth()%2==1)
			hasHalf = true;

		int only_once = 0;
		for (int i = 0; i < (World.getThePlayer().getMaxHealth()/2); i++) {
			if(i<full_hearts)
			{
				g.drawImage(heart, 400 + (17 * i), 75, null);
			} else {
				if(hasHalf && only_once == 0){
					g.drawImage(half_heart, 400 + (17 * i), 75, null);
					only_once++;
				} else {
					g.drawImage(empty_heart, 400 + (17 * i), 75, null);
				}
			}	
		}
	}

	/** Draws the tri-force logo. */
	private static void drawTriForce(Graphics g) {
		// TODO: stub
		// it needs to draw the triangle thing
	}

	/** Draws the HUD on top of the Graphics object. */
	public static void drawHud(Graphics g) {
		drawHealth(g);
		drawTriForce(g);
	}

	/** HUD may not be instantiated; all methods are static. */
	private HUD() {
	}
}