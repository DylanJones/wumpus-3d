import display.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;
import javax.swing.*;

import entity.*;


public class Main {
	private static Clip clip;
	private static AudioInputStream audioIn;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		JPanel panel = new WumpusPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
		/* TEMPORARY, DELETE SOON */
		new EntityGremlin(100, 100);
		new EntityGremlin(100, 500);
		new EntityGremlin(500, 100);
		new EntityGremlin(500, 500);
		for (;;) {
			try {
				playSound("assets/SplashScreenTheme.wav");
				Thread.sleep((long) ((1000 * 60) * 3.51));
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}

	private static void playSound(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioIn = AudioSystem.getAudioInputStream(Main.class.getResource(file));
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
		/*AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;

		ContinuousAudioDataStream loop = null;

		try {
			InputStream test = new FileInputStream(file);
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Why are you starting it on a null object
		// MGP.start(loop);*/
	}
}