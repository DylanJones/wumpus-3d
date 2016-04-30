package display;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class HUD {
	private static Image heart = null;
	static	{
		try {
			heart = ImageIO.read(new File("assets/items/heart.png")).getScaledInstance(20, 20,
					Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.print("Error reading item image files");
			System.exit(1);
		}
	}
	/** Draws the health bar and anything else related to health. */
	private static void drawHealth(Graphics g) {
		for(int i = 0; i < World.getThePlayer().getHealth(); i++)
		{
			g.drawImage(heart,595-(25*i),10,null);
		}
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