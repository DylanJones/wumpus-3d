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
	private KeyboardHandler kb;

	public WumpusPanel() {
		super();
		kb = new KeyboardHandler();
		addKeyListener(kb);
		setFocusable(true);
		World.startTicker(this, kb);
		World.loadWorld("8H.wld");
	}

	@Override
	public void paintComponent(Graphics g) {
		// Clear the screen
		g.setColor(Color.BLACK);
		g.setFont(Font.getFont(Font.SANS_SERIF));
		g.drawString("FIX MEEEE", 100, 100);
		HUD.drawHud(g);
		for (Entity e : World.getAllEntities()) {
			e.draw(g);
		}
	}
}
