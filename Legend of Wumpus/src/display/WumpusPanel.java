package display;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
/** This is the class which does the.... something */
public class WumpusPanel extends JPanel {
	private Timer myTimer;
	private KeyboardHandler myKeyboardHandler;

	public WumpusPanel() {
		super();
		myKeyboardHandler = new KeyboardHandler();
		this.addKeyListener(myKeyboardHandler);
		this.setFocusable(true);
		myTimer = new Timer(10, new Tick(this, myKeyboardHandler));
		myTimer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Player.draw(g);
	}
}
