package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

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
		container.update(container.getGraphics());
	}

	private void movePlayer() {
		if (kb.isKeyPressed('w')) {
			Player.y = (Player.y < 0) ? container.getHeight() : Player.y - MOVEMENT_DIST;
		}
		if (kb.isKeyPressed('s')) {
			Player.y = (Player.y > 600) ? 0 : Player.y + MOVEMENT_DIST;
		}
		if (kb.isKeyPressed('a')) {
			Player.x = (Player.x < 0) ? container.getWidth() : Player.x - MOVEMENT_DIST;
		}
		if (kb.isKeyPressed('d')) {
			Player.x = (Player.x > 600) ? 0 : Player.x + MOVEMENT_DIST;
		}
	}

}
