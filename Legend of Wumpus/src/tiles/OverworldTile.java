package tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public enum OverworldTile implements WorldTile{
	// Misc objects
	stairs1("stairs1.png", false, 0x01), 
	square_shrub("square_shrub.png", true, 0x02), 
	stairs2("stairs2.png", false, 0x03), 
	round_shrub("round_shrub.png", true, 0x04), 
	statue("statue.png", true, 0x05), 
	ground("ground.png", false, 0x06),
	// Tree tiles
	trees_nw("trees_nw.png", true, 0x07), 
	trees_sw("trees_sw.png", true, 0x08), 
	trees_ne("trees_ne.png", true, 0x09), 
	trees_se("trees_se.png", true, 0x0A),
	trees_north("trees_north.png", true, 0x0B), 
	trees("trees.png", true, 0x0C),
	// Lake tiles
	lake_ne("lake_ne.png", true, 0x0D), 
	lake_se("lake_se.png", true, 0x0E), 
	lake_nw("lake_nw.png", true, 0x0F),
	lake_sw("lake_sw.png", true, 0x10), 
	lake_north("lake_north.png", true, 0x11),
	lake_south("lake_south.png", true, 0x12), 
	lake_east("lake_east.png", true, 0x13), 
	lake_west("lake_west.png", true, 0x14),
	shore_nw("shore_nw.png", false, 0x15), 
	shore_ne("shore_ne.png", false, 0x16), 
	shore_sw("shore_sw.png", false, 0x17), 
	shore_se("shore_se.png", false, 0x18);
	
	public static WorldTile getTileFromCode(int code) {
		switch (code) {
		case 0x01:
			return stairs1;
		case 0x02:
			return square_shrub;
		case 0x03:
			return stairs2;
		case 0x04:
			return round_shrub;
		case 0x05:
			return statue;
		case 0x06:
			return ground;
		case 0x07:
			return trees_nw;
		case 0x08:
			return trees_sw;
		case 0x09:
			return trees_ne;
		case 0x0A:
			return trees_se;
		case 0x0B:
			return trees_north;
		case 0x0C:
			return trees;
		case 0x0D:
			return lake_ne;
		case 0x0E:
			return lake_se;
		case 0x0F:
			return lake_nw;
		case 0x10:
			return lake_sw;
		case 0x11:
			return lake_north;
		case 0x12:
			return lake_south;
		case 0x13:
			return lake_east;
		case 0x14:
			return lake_west;
		case 0x15:
			return shore_nw;
		case 0x16:
			return shore_ne;
		case 0x17:
			return shore_sw;
		case 0x18:
			return shore_se;
		default:
			return null;
		}
	}

	// Enum polymorphism things
	private Image myImage;
	private int myCode;
	private boolean isSolid;

	@Override
	public void draw(int x, int y, Graphics g) {
		int[] screenCoordinates = World.getScreenCoordinates(x, y);
		g.drawImage(myImage, screenCoordinates[0], screenCoordinates[1], null); // each
																				// tile
																				// is
																				// 16x16
	}

	@Override
	public boolean isSolid() {
		return isSolid;
	}

	@Override
	public int getCode() {
		return myCode;
	}

	private OverworldTile(String imageName, boolean solid, int byteCode) {
		try {
			myImage = ImageIO.read(new File("assets/tiles/overworld/" + imageName)).getScaledInstance(32, 32,
					Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.println("Error reading image file: assets/tiles/overworld/" + imageName);
			System.exit(1);
		}
		isSolid = solid;
		this.myCode = byteCode;
	}
}
