package display;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class HUD {
	private static Image heart = null;

	static {
		//Read heart image
	}

	/**
	 * Draws the health bar and anything else related to health. (e.g. amount)
	 */
	private static void drawHealth(Graphics g) {
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