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
	/**
	* Called when a ket is pressed down
	*/
	public void keyPressed(KeyEvent e) {
		keysPressed.put(true);
	}

	@Override
	/** Called whenerver a key is released */
	public void keyReleased(KeyEvent e) {
		keysPressed.put(e.getKeyChar(), false);
	}

	@Override
	/** Required by interface, but we don't need it */
	public void keyTyped(KeyEvent arg0) {
		//Do nothing
	}

	/**
	 * Returns true if the specified key is pressed, else false. Does not work
	 * with modifiers or arrows.
	 */
	public boolean isKeyPressed(char key) {
	}
}