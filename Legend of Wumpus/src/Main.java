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
		frame.setSize(640, 480);
		frame.setVisible(true);
		// frame.setResizable(false);
		/* TEMPORARY, DELETE SOON */
		new EntityGremlin(100, 100);
		new EntityGremlin(100, 480);
		new EntityGremlin(480, 100);
		new EntityGremlin(480, 480);
		MusicPlayer.changePlayingMusic("/assets/music/SplashScreenTheme.wav");
		Thread.sleep(1000*10);
		MusicPlayer.changePlayingMusic("/assets/music/DungeonTheme.wav");
		//  for (;;) { 
		//  	try { 
		//  		MusicPlayer.changePlayingMusic("assets/music/SplashScreenTheme.wav");
		//  		Thread.sleep((long) ((1000 * 60) * 3.51)); 
		//  	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) { 
		//  		e.printStackTrace(); 
		//  	} catch(Exception e) { 
		//  		System.err.println("Unexpecteed errrrorrrr");
		//  		e.printStackTrace(); 
		// 	}
		// }
	}
}