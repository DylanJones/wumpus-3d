import display.*;

import javax.swing.*;
import entity.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		JPanel panel = new WumpusPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(640, 480);
		frame.setVisible(true);
		// frame.setResizable(false);
		/* TEMPORARY, DELETE SOON */
		new EntityGremlin(100, 100, 200, 200);
		/*
		 * MusicPlayer.changePlayingMusic("/assets/music/SplashScreenTheme.wav")
		 * ; Thread.sleep(1000*10);
		 * MusicPlayer.changePlayingMusic("/assets/music/DungeonTheme.wav");
		 */
	}
}