package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * This class listens for keyboard input and keeps track of the pressed keys.
 */
public class KeyboardHandler implements KeyListener {
	private HashMap<Character, Boolean> keysPressed = new HashMap<Character, Boolean>();

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("KeyPress: " + e.getKeyChar());
		if (keysPressed.containsKey(e.getKeyChar())) {
			keysPressed.replace(e.getKeyChar(), true);
		} else {
			keysPressed.put(e.getKeyChar(), true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("KeyRelease: " + e.getKeyChar());
		keysPressed.replace(e.getKeyChar(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	public boolean isKeyPressed(char key) {
		boolean pressed = false;
		try {
			pressed = keysPressed.get(key);
		} catch (NullPointerException e) {
//			e.printStackTrace();
		}
		return pressed;
	}
}
