package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import entity.Entity;

/**
 * Draws a 2D map on the screen.
 */
public class MapPanel extends JPanel {
	private static final long serialVersionUID = 4170582039177761054L;

	// Documentation provided by superclass
	@Override
	public void paintComponent(Graphics g) {
		// Create a buffer to prevent flickering
		BufferedImage buffer = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferGraphics = buffer.createGraphics();
		TileRenderer.renderTiles2D(bufferGraphics);
		HUD.drawHud(bufferGraphics);
		for (Entity e : World.getAllEntities()) {
			e.draw(bufferGraphics);
		}
		g.drawImage(buffer, 0, 0, null);
	}
}
