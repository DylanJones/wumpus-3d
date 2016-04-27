package display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class should handle all of the things related to the Player. It should
 * NOT handle drawing the GUI (beyond drawing the player sprite).
 */
public final class Player {
	private static Image northImage;
	private static Image southImage;
	private static Image eastImage;
	private static Image westImage;
	public int facing = World.NORTH;
	public static int x = 0;
	public static int y = 0;

	static {
		try {
			northImage = ImageIO.read(new File("assets/wumpus/north.png"));
			southImage = ImageIO.read(new File("assets/wumpus/north.png"));
			eastImage = ImageIO.read(new File("assets/wumpus/north.png"));
			westImage = ImageIO.read(new File("assets/wumpus/north.png"));
		} catch (IOException e) {
		}
	}

	public static void draw(Graphics g) {
		g.drawImage(northImage, x, y, null);
	}
}
