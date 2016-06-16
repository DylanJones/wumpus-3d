package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * This class listens for keyboard input and keeps track of the pressed keys.
 */
public class KeyboardHandler implements KeyListener {
	// Map of keys and their states.
	private volatile HashMap<Character, Boolean> keysPressed = new HashMap<Character, Boolean>();

	@Override
	public void keyPressed(KeyEvent e) {
		// Only execute once per keypress
		keysPressed.put(e.getKeyChar(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed.put(e.getKeyChar(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	/**
	 * Returns true if the specified key is pressed, else false. Does not work
	 * with modifiers or arrows.
	 */
	public boolean isKeyPressed(char key) {
		boolean pressed = false;
		try {
			pressed = keysPressed.get(key);
		} catch (NullPointerException e) {// The key doesn't exist; return false
		}
		return pressed;
	}
}