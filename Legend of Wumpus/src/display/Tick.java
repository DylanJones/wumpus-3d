package display;

import entity.*;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This class does the majority of the world engine work. */
public class Tick implements ActionListener {
	private JPanel container;
	private KeyboardHandler kb;

	public Tick(JPanel parent, KeyboardHandler kb) {
		container = parent;
		this.kb = kb;
	}

	/** Absolutely NOTHING related to GUI may happen here. */
	public void actionPerformed(ActionEvent e) {
		movePlayer();
		entityTick();
		collideEntities();
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w'))
			World.getThePlayer().move(0.15);
		if (kb.isKeyPressed('q'))
			World.getThePlayer().attack();
	}

	/**
	 * Takes all the entities in the world and computes if any are touching. If
	 * they are touching, call <entity>.collide().
	 */
	private void collideEntities() {
		for (Entity e : World.getAllEntities()) {
			for (Entity x : World.getAllEntities()) {
				if (x == e)
					continue;// So it doesn't collide with itself
				// If they are within each others boxes
				if ((Math.abs(e.getX() - x.getX()) * 2 < e.getWidth() + x.getWidth())
						&& (Math.abs(e.getY() - x.getY()) * 2 < e.getHeight() + x.getHeight())) {
					e.collide(x);
				}
			}
		}
	}

	private void entityTick() {
		for (Entity e : World.getAllEntities()) {
			e.tick();
		}
	}

}
