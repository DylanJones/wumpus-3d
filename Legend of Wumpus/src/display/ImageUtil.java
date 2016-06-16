package display;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageUtil {
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	/**
	 * Rotates img by angle degrees. Returns a copy of the rotated image.
	 * 
	 * @param image
	 *            The image to be rotated
	 * @param angle
	 *            The angle to rotate by
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
