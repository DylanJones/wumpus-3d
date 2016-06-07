package display;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.*;

/** This class is a helper for playing sound effects and music files. */
public class MusicPlayer {
	private static AudioInputStream audioIn;
	private static Clip clip;
	private static String currentMusic = null;

	/**
	 * Plays the sound in file once.
	 * 
	 * @param file
	 *            the sound file to play
	 */
	public static void playSoundEffect(String file) {
		try {
			AudioInputStream tempAudioIn = AudioSystem
					.getAudioInputStream(MusicPlayer.class.getResource(file));
			Clip tempClip = AudioSystem.getClip();
			tempClip.open(tempAudioIn);
			tempClip.setFramePosition(0);
			tempClip.loop(0);
			tempClip.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to find music file " + file);
		}
	}

	private static void loopSound(String file)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException, URISyntaxException {
		audioIn = AudioSystem.getAudioInputStream(MusicPlayer.class
				.getResource(file));
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Set the looping music to the music in file. If the music is null, stop
	 * playing all music.
	 * 
	 * @param newMusic
	 *            the music file to loop
	 */
	public static void changePlayingMusic(String newMusic) {
		try {
			if (newMusic == null) {
				currentMusic = newMusic;
				if (clip != null)
					clip.stop();
				return;
			}
			if (newMusic.equals(currentMusic))
				return;
			if (clip != null)
				clip.stop();
			loopSound(newMusic);
			currentMusic = newMusic;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error playing music " + newMusic);
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
	 * * Get the currently playing music. If no music is playing, returns null. @return
	 * the currently playing music
	 */
	public String getPlayingMusic() {
		return currentMusic;
	}

	/** MusicPlayer may not be instantiated. */
	private MusicPlayer() {
	}
}