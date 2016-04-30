package display;

import java.awt.Color;
import java.awt.Graphics;

public final class HUD {

	/** Draws the health bar and anything else related to health. */
	private static void drawHealth(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Heath: " + World.getThePlayer().getHealth(), 500, 10);
	}

	/** Draws the tri-force logo. */
	private static void drawTriForce(Graphics g) {
		// TODO: stub
		//it needs to draw the triangle thing
	}

	/** Draws the HUD on top of the Graphics object. */
	public static void drawHud(Graphics g) {
		drawHealth(g);
		drawTriForce(g);
	}
}
