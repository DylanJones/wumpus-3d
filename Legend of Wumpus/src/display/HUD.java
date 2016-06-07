package display;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** This class handles rendering of the health and triforce on the screen. */
public final class HUD {
	private static Image heart = null;
	private static Image half_heart = null;
	private static Image empty_heart = null;
	private static Image life_text = null;
	private static Image triforce = null;

	static {
		try {
			heart = ImageIO.read(new File("assets/items/heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			half_heart = ImageIO.read(new File("assets/items/half_heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			empty_heart = ImageIO.read(new File("assets/items/empty_heart.png")).getScaledInstance(16, 16, Image.SCALE_REPLICATE);
			life_text = ImageIO.read(new File("assets/text/HUD_text.png")).getScaledInstance(96, 20, Image.SCALE_REPLICATE);
			triforce = ImageIO.read(new File("assets/items/triforce_orange.png")).getScaledInstance(20, 20, Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.print("Error loading files for HUD");
			System.exit(1);
		}
	}

	/**
	 * Draws the health bar and anything else related to health. (e.g. amount)
	 */
	private static void drawHealth(Graphics g) {
		g.drawImage(life_text, 366, 30, null);

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
				g.drawImage(heart, 352 + (17 * i), 80, null);
			} else {
				if(hasHalf && only_once == 0){
					g.drawImage(half_heart, 352 + (17 * i), 80, null);
					only_once++;
				} else {
					g.drawImage(empty_heart, 352 + (17 * i), 80, null);
				}
			}	
		}
	}

	/** Draws the tri-force logo as player gets them. */
	private static void drawTriForce(Graphics g) {
		int itemsToDraw = World.getThePlayer().getTriforces();
		for(int i = 0; i < itemsToDraw; i++)
			g.drawImage(triforce, 100 + (22 * i), 80, null);
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