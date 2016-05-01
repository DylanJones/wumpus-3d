package display;

import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ConcurrentModificationException;

/** This class does the majority of the world engine work. */
public class Tick implements ActionListener {
	private WumpusPanel container;
	private KeyboardHandler kb;

	public Tick(WumpusPanel parent, KeyboardHandler kb) {
		container = parent;
		this.kb = kb;
	}

	/** Absolutely NOTHING related to GUI may happen here. */
	public void actionPerformed(ActionEvent e) {
		movePlayer();
		entityTick();
		collideEntities();
		container.update(container.getGraphics());
		if (World.getGameState() == 2) { // Stop the world engine
			// This is super bad practice, need to fix
			//ticker should be private
			container.ticker.stop();
		}
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w'))
			World.getThePlayer().move(5);
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
				if((Math.abs(e.getX() - x.getX()) * 2 < e.getWidth() + x.getWidth()) &&
						(Math.abs(e.getY() - x.getY()) * 2 < e.getHeight() + x.getHeight())){
//				if (Math.abs(e.getX() - x.getX()) + Math.abs(e.getY() - x.getY()) < 30) {
					e.collide(x);
					// System.out.println("collision");
				}
			}
		}
	}

	private void entityTick() {
		try {
			for (Entity e : World.getAllEntities()) {
				e.tick();
			}
		} catch (ConcurrentModificationException exception) {
			System.err.println("errors were encountered");
//			exception.printStackTrace();
		}
	}

}
