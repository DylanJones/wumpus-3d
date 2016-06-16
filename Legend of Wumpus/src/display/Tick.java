package display;

import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/** This class supervises the majority of the world engine work. */
public class Tick implements ActionListener {
	private JPanel container;
	private KeyboardHandler kb;
	private long lastITime = 0;

	private static boolean playerHasControl = true;

	public Tick(JPanel parent, KeyboardHandler kb) {
		container = parent;
		this.kb = kb;
	}

	/** Absolutely NOTHING related to GUI may happen here. */
	public void actionPerformed(ActionEvent e) {
		if (playerHasControl)
			movePlayer();
		entityTick();
		collideEntities();
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w'))
			World.getThePlayer().move(0.15);
		if (kb.isKeyPressed('a'))
			World.getThePlayer().turnLeft();
		if (kb.isKeyPressed('d'))
			World.getThePlayer().turnRight();
		if (kb.isKeyPressed('q'))
			World.getThePlayer().attack();
		if (kb.isKeyPressed('i')
				&& System.currentTimeMillis() - lastITime > 1000) {
			lastITime = System.currentTimeMillis();
			World.getThePlayer().setCanTakeDamage(
					!World.getThePlayer().getCanTakeDamage());
		}
	}

	/**
	 * Takes all the entities in the world and computes if any are touching the
	 * player. If they are touching, call <entity>.collide().
	 */
	private void collideEntities() {
		for (Entity e : World.getAllEntities()) {
			for (Entity x : World.getAllEntities()) {
				if (x == e)
					continue;
				if ((Math.abs(e.getX() - x.getX()) * 2 < e.getWidth()
						+ x.getWidth())
						&& (Math.abs(e.getY() - x.getY()) * 2 < e.getHeight()
								+ x.getHeight())) {
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

	/**
	 * Set wheter or not the player can be controlled by the keyboard
	 * 
	 * @param control
	 *            whether or not the player can be controlled
	 */
	public static void setPlayerControl(boolean control) {
		playerHasControl = control;
	}

	/**
	 * Get wheter or not the player can be controlled by the keyboard
	 * 
	 * @return whether or not the player can be controlled
	 */
	public static boolean getPlayerControl() {
		return playerHasControl;
	}
}
