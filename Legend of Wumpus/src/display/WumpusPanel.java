package display;

import entity.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
/**
 * This is the class which loads everything into the JPanel and sets up a few
 * global variables. There should only ever be one instance of WumpusPanel.
 */
public class WumpusPanel extends JPanel {
	private KeyboardHandler kb;
	private boolean start;

	public WumpusPanel() {
		super();
		kb = new KeyboardHandler();
		addKeyListener(kb);
		setFocusable(true);
		World.startTicker(this, kb);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Draw tiles
		BufferedImage buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferGraphics = buffer.createGraphics();
		World.renderTiles(bufferGraphics);
		HUD.drawHud(bufferGraphics);
		for (Entity e : World.getAllEntities()) {
			e.draw(bufferGraphics);
		}
		g.drawImage(buffer, 0, 0, null);
	}
}
