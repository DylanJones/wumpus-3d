package display;

import entity.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
/**
 * This is the class which loads everything into the JPanel and sets up a few
 * global variables. There should only ever be one instance of WumpusPanel.
 */
public class WumpusPanel extends JPanel {
	// This SHOULD be private. We must make it so.
	private static KeyboardHandler kb;

	public WumpusPanel() {
		super();
		kb = new KeyboardHandler();
		addKeyListener(kb);
		setFocusable(true);
		World.startTicker(this, kb);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Clear the screen
		if (World.getBackgroundImage() != null) {
			g.drawImage(World.getBackgroundImage(), 0, 0, null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		HUD.drawHud(g);
		for (Entity e : World.getAllEntities()) {
			e.draw(g);
		}
	}
}
