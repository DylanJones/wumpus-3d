package display;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageUtil {
	/**
	 * Rotates img by angle degrees. Returns a copy of the rotated image.
	 * 
	 * @param image
	 *            The image to be rotated
	 * @param angleDegrees
	 *            The angle in degrees
	 * @return The rotated image
	 */
	public static BufferedImage rotate(BufferedImage image, Angle angle) {
		return rotate(image, angle.getDegrees());
	}
	
	/**
	 * Rotates img by angle degrees. Returns a copy of the rotated image.
	 * 
	 * @param image
	 *            The image to be rotated
	 * @param angleDegrees
	 *            The angle in degrees
	 * @return The rotated image
	 */
	public static BufferedImage rotate(BufferedImage image, double angleDegrees) {
		double angleRadians = Math.toRadians(angleDegrees);
		// Find new width and height
		double sin = Math.abs(Math.sin(angleRadians)), cos = Math.abs(Math
				.cos(angleRadians));
		int w = image.getWidth(), h = image.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math
				.floor(h * cos + w * sin);
		// Use GraphicsConfiguration to create a compatable BufferedImage
		GraphicsConfiguration gc = getDefaultConfiguration();
		BufferedImage result = gc.createCompatibleImage(neww, newh,
				Transparency.TRANSLUCENT);
		// Use Graphics2D to rotate "image" and draw it on the new one
		Graphics2D g = result.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(angleRadians, w / 2, h / 2);
		g.drawRenderedImage(image, null);
		g.dispose();
		// Return the new image
		return result;
	}

	private static GraphicsConfiguration getDefaultConfiguration() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		return gd.getDefaultConfiguration();
	}
}
