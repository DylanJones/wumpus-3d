package display;

import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/** This class does the majority of the world engine work. */
public class Tick implements ActionListener {
	private JPanel container;
	private KeyboardHandler kb;
	public final int MOVEMENT_DIST = 1;

	public Tick(JPanel parent, KeyboardHandler kb) {
		container = parent;
		this.kb = kb;
	}

	public void actionPerformed(ActionEvent e) {
		movePlayer();
		collideEntities();
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w')) {
			WumpusPanel.thePlayer.setY((WumpusPanel.thePlayer.getY() < 0) ? container.getHeight() : WumpusPanel.thePlayer.getY() - MOVEMENT_DIST);
		}
		if (kb.isKeyPressed('s')) {
			WumpusPanel.thePlayer.setY((WumpusPanel.thePlayer.getY() > 600) ? 0 : WumpusPanel.thePlayer.getY() + MOVEMENT_DIST);
		}
		if (kb.isKeyPressed('a')) {
			WumpusPanel.thePlayer.setX((WumpusPanel.thePlayer.getX() < 0) ? container.getWidth() : WumpusPanel.thePlayer.getX() - MOVEMENT_DIST);
		}
		if (kb.isKeyPressed('d')) {
			WumpusPanel.thePlayer.setX((WumpusPanel.thePlayer.getX() > 600) ? 0 : WumpusPanel.thePlayer.getX() + MOVEMENT_DIST);
		}
	}

	/**
	 * Takes all the entities in the world and computes if any are touching. If
	 * they are touching, call <entity>.collide().
	 */
	private void collideEntities() {
		for (Entity e : World.getAllEntities()) {
			// STUB; still needs to be implemented
			for (Entity x : World.getAllEntities()) {
				if(x == e) continue;//So it doesn't collide with itself
				//If they are less than 5 pixels apart...
				if(Math.abs(e.getX() - x.getX()) + Math.abs(e.getY() - x.getY()) < 30){
					e.collide(x);
//					System.out.println("collision");
//					System.out.println("distance: " + Math.abs(e.getX() - x.getX()) + Math.abs(e.getY() - x.getY()));
				}
//				if(x instanceof Player)
//					System.out.println(x.getX());
			}
		}
	}

}
