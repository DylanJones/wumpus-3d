package display;

import java.io.IOException;

import javax.sound.sampled.*;

public class MusicPlayer {
	private static AudioInputStream audioIn;
	private static Clip clip;
	private static String currentMusic = null;

	/*private static void playSound(String file)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream tempAudioIn = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource(file));
		Clip tempClip = AudioSystem.getClip();
		tempClip.open(tempAudioIn);
		tempClip.setFramePosition(0);
		tempClip.loop(0);
		tempClip.start();
	}*/

	private static void loopSound(String file)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioIn = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource(file));
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}

	public static void changePlayingMusic(String newMusic) {
		try {
			if (clip != null)
				clip.stop();
			loopSound(newMusic);
			currentMusic = newMusic;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
			System.err.println("Error playing music");
		}
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
