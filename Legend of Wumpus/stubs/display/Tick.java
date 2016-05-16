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
		//Set container and kb
	}

	/** Absolutely NOTHING related to GUI may happen here. */
	public void actionPerformed(ActionEvent e) {
		movePlayer();
		entityTick();
		collideEntities();
		//If the game state is 2, then stop the game
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		//If w is pressed, then move
		//If q is pressed, then player.attack
	}

	/**
	 * Takes all the entities in the world and computes if any are touching. If
	 * they are touching, call <entity>.collide().
	 */
	private void collideEntities() {
	}

	private void entityTick() {
	}

}
