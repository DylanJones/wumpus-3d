package display;

import java.io.IOException;

import javax.sound.sampled.*;

public class MusicPlayer {
	private static AudioInputStream audioIn;
	private static Clip clip;
	private static String currentMusic = null;

	/**
	* Sethe looping sound (current soundtrack) to the specified music file.
	* @param file The filename of the new soiundtrack
	* 
	*/
	private static void loopSound(String file)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	}

	/**
	* Change the soundtrack to the new one
	*/
	public static void changePlayingMusic(String newMusic) {
	}

	/** @return is music playing or not */
	public static boolean isMusicPlaying() {
		if (clip != null) {
			return true;
		}
		return false;
	}

	/**
	 * Get the currently playing music. If no music is playing, returns null.
	 * 
	 * @return the currently playing music
	 */
	public String getPlayingMusic() {
		return currentMusic;
	}

	/** MusicPlayer may not be instantiated. */
	private MusicPlayer() {
	}
}
