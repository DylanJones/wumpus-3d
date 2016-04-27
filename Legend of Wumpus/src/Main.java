import display.*;
import javax.swing.*;

public class Main {
	static JFrame frame;

	public static void main(String[] args) {
		frame = new JFrame();
		JPanel panel = new WumpusPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}