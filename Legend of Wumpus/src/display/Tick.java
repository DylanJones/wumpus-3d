package display;

import entity.*;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This class does the majority of the world engine work. */
public class Tick implements ActionListener {
	private JPanel container;
	private KeyboardHandler kb;
	private boolean wasQPressed = false;

	public Tick(JPanel parent, KeyboardHandler kb) {
		container = parent;
		this.kb = kb;
	}

	/** Absolutely NOTHING related to GUI may happen here. */
	public void actionPerformed(ActionEvent e) {
		movePlayer();
		entityTick();
		collideEntities();
		if (WorldBackend.getGameState() == 2) { // Stop the world engine
			WorldBackend.stopTicker();
		}
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w'))
			WorldBackend.getThePlayer().move(5);
		if(!wasQPressed && kb.isKeyPressed('q')){
			wasQPressed = true;
			WorldBackend.getThePlayer().attack();
		}else {
			wasQPressed = false;
		}
	}

	/**
	 * Takes all the entities in the world and computes if any are touching. If
	 * they are touching, call <entity>.collide().
	 */
	private void collideEntities() {
		for (Entity e : WorldBackend.getAllEntities()) {
			for (Entity x : WorldBackend.getAllEntities()) {
				if (x == e)
					continue;// So it doesn't collide with itself
				// If they are within each others boxes
				if ((Math.abs(e.getX() - x.getX()) * 2 < e.getWidth() + x.getWidth())
						&& (Math.abs(e.getY() - x.getY()) * 2 < e.getHeight() + x.getHeight())) {
					// if (Math.abs(e.getX() - x.getX()) + Math.abs(e.getY() -
					// x.getY()) < 30) {
					e.collide(x);
					// System.out.println("collision");
				}
			}
		}
	}

	private void entityTick() {
		for (Entity e : WorldBackend.getAllEntities()) {
			e.tick();
		}
	}

}
