package display;

import entity.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
/**
 * This is the class which loads everything into the JPanel and should probably
 * handle graphics, although we might need a separate class for that.
 */
public class WumpusPanel extends JPanel {
	private Timer ticker;
	private KeyboardHandler kb;
	public static Player thePlayer;

	public WumpusPanel() {
		super();
		kb = new KeyboardHandler();
		this.addKeyListener(kb);
		this.setFocusable(true);
		ticker = new Timer(10, new Tick(this, kb));
		ticker.start();
		thePlayer = new Player();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (Entity e : World.getAllEntities()) {
			e.draw(g);
		}
	}
}
