package display;

import javax.swing.*;
import java.awt.*;

public final class Player {
	public static ImageIcon north;
	public static ImageIcon south;
	public static ImageIcon east;
	public static ImageIcon west;
	static {
		north = new ImageIcon("assets/wumpus/north.png");
		south = new ImageIcon("assets/wumpus/south.png");
		east = new ImageIcon("assets/wumpus/east.png");
		west = new ImageIcon("assets/wumpus/west.png");
	}
	
	public static void draw(Graphics g) {
		
	}
}
