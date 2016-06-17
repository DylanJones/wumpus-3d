package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * This is the class which loads everything into the JPanel and sets up a few
 * global variables. There should only ever be one instance of WumpusPanel.
 */
public class WumpusPanel extends JPanel {
	private static final long serialVersionUID = 4170582039177761054L;
	KeyboardHandler kb;
	private JButton startButton;

	/** Default constructor for WumpusPanel. Starts the title screen animation. */
	public WumpusPanel() {
		super();
		startButton = new JButton(new ImageIcon(
				WumpusPanel.class.getResource("/assets/title.gif")));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				World.setGameState(1);
			}
		});
		kb = new KeyboardHandler();
		addKeyListener(kb);
		setFocusable(true);
		World.setPanel(this);
		World.setGameState(0);
	}

	// Documentation provided by superclass
	@Override
	public void paintComponent(Graphics g) {
		// Draw tiles
		BufferedImage buffer = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferGraphics = buffer.createGraphics();
//		long start = System.nanoTime();
		TileRenderer.renderTiles(bufferGraphics);
//		long stop = System.nanoTime();
//		System.out.println("Raycasting took " + (stop - start) / 1000000.0 + " milliseconds");
		HUD.drawHud(bufferGraphics);
//		for (Entity e : World.getAllEntities()) {
//			e.draw(bufferGraphics);
//		}
		World.getThePlayer().setCanTakeDamage(false); //because we're not rendering entites
		g.drawImage(buffer, 0, 0, null);
	}

	/** Hide the start game button. */
	public void hideStartButton() {
		this.remove(startButton);
	}

	/** Show the start game button. */
	public void showStartButton() {
		this.add(startButton);
	}

}
