package display;

import entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
/**
 * This is the class which loads everything into the JPanel and sets up a few
 * global variables. There should only ever be one instance of WumpusPanel.
 */
public class WumpusPanel extends JPanel {
	KeyboardHandler kb;
	private boolean start;
	private JButton startButton;

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

	@Override
	public void paintComponent(Graphics g) {
		// Draw tiles
		BufferedImage buffer = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D bufferGraphics = buffer.createGraphics();
		World.renderTiles(bufferGraphics);
		HUD.drawHud(bufferGraphics);
		for (Entity e : World.getAllEntities()) {
			e.draw(bufferGraphics);
		}
		g.drawImage(buffer, 0, 0, null);
	}

	public void hideButton() {
		this.remove(startButton);
	}

	public void showButton() {
		this.add(startButton);
	}
}
