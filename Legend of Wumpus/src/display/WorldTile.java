package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

/**
 * This is a more complex enum that contains the tiles for the world. Each
 * constant has an ID, an image, and an isSolid property. The world is rendered
 * based on these properties.
 */
public enum WorldTile {
	// ///////////////////////////OVERWORLD/TREE WORLD///////////////////////
	// Misc objects
	stairs1("stairs1.png", false, 0x01), square_shrub("square_shrub.png", true,
			0x02), stairs2("stairs2.png", false, 0x03), round_shrub(
			"round_shrub.png", true, 0x04), statue("statue.png", true, 0x05), ground(
			"ground.png", false, 0x06),
	// Tree tiles
	trees_nw("trees_nw.png", true, 0x07), trees_sw("trees_sw.png", true, 0x08), trees_ne(
			"trees_ne.png", true, 0x09), trees_se("trees_se.png", true, 0x0A), trees_north(
			"trees_north.png", true, 0x0B), trees("trees.png", true, 0x0C),
	// Lake tiles
	lake_ne("lake_ne.png", true, 0x0D), lake_se("lake_se.png", true, 0x0E), lake_nw(
			"lake_nw.png", true, 0x0F), lake_sw("lake_sw.png", true, 0x10), lake_north(
			"lake_north.png", true, 0x11), lake_south("lake_south.png", true,
			0x12), lake_east("lake_east.png", true, 0x13), lake_west(
			"lake_west.png", true, 0x14), shore_nw("shore_nw.png", false, 0x15), shore_ne(
			"shore_ne.png", false, 0x16), shore_sw("shore_sw.png", false, 0x17), shore_se(
			"shore_se.png", false, 0x18), lake("lake.png", true, 0x19), door(
			"door.png", false, 0x1A),
	// //////////////////////ROCK WORLD///////////////////////////
	rbridge("rbridge.png", false, 0x1B),
	// The face thing
	rface_00("rface_00.png", true, 0x1C), rface_01("rface_01.png", true, 0x1D), rface_10(
			"rface_10.png", true, 0x1E), rface_20("rface_20.png", true, 0x1F), rface_21(
			"rface_21.png", true, 0x20),
	// Lake
	rlake("rlake.png", true, 0x21), rlake_east("rlake_east.png", true, 0x22), rlake_west(
			"rlake_west.png", true, 0x23), rlake_south("rlake_south.png", true,
			0x24), rlake_north("rlake_north.png", true, 0x25), rlake_ne(
			"rlake_ne.png", true, 0x26), rlake_nw("rlake_nw.png", true, 0x27), rlake_se(
			"rlake_se.png", true, 0x28), rlake_sw("rlake_sw.png", true, 0x29),
	// Shore
	rshore_ne("rshore_ne.png", false, 0x2A), rshore_se("rshore_se.png", false,
			0x2B), rshore_nw("rshore_nw.png", false, 0x2C), rshore_sw(
			"rshore_sw.png", false, 0x2D),
	// Those rock things that are everywhere
	rock("rock.png", true, 0x2E), rock_ne("rock_ne.png", true, 0x2F), rock_nw(
			"rock_nw.png", true, 0x30), rock_se("rock_se.png", true, 0x31), rock_sw(
			"rock_sw.png", true, 0x32), rock_north("rock_north.png", true, 0x33),
	// Sand in rock world
	rsand1("rsand1.png", false, 0x34), rsand2("rsand2.png", false, 0x35), rsand_east(
			"rsand_east.png", false, 0x36), rsand_west("rsand_west.png", false,
			0x37), rsand_north("rsand_north.png", false, 0x38), rsand_south(
			"rsand_south.png", false, 0x39), rsand_ne("rsand_ne.png", false,
			0x3A), rsand_nw("rsand_nw.png", false, 0x3B), rsand_se(
			"rsand_se.png", false, 0x3C), rsand_sw("rsand_sw.png", false, 0x3D),
	// more random things
	rshrub("rshrub.png", true, 0x3E), rstatue("rstatue.png", true, 0x3F),
	// Tree dungeon entrence
	rtree_00("rtree_00.png", true, 0x40), rtree_01("rtree_01.png", true, 0x41), rtree_10(
			"rtree_10.png", true, 0x42), rtree_20("rtree_20.png", true, 0x43), rtree_21(
			"rtree_21.png", true, 0x44), square_rock("square_rock.png", true,
			0x45), rwaterfall("rwaterfall.png", true, 0x46), stairs3(
			"stairs3.png", false, 0x47), stairs4("stairs4.png", false, 0x48);

	/**
	 * Convert from an int code to a WorldTile
	 * 
	 * @param code
	 *            the int code to find out
	 * @return the corresponding WorldTile
	 */
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
		case 0x19:
			return lake;
		case 0x1A:
			return door;
		case 0x1B:
			return rbridge;
		case 0x1C:
			return rface_00;
		case 0x1D:
			return rface_01;
		case 0x1E:
			return rface_10;
		case 0x1F:
			return rface_20;
		case 0x20:
			return rface_21;
		case 0x21:
			return rlake;
		case 0x22:
			return rlake_east;
		case 0x23:
			return rlake_west;
		case 0x24:
			return rlake_south;
		case 0x25:
			return rlake_north;
		case 0x26:
			return rlake_ne;
		case 0x27:
			return rlake_nw;
		case 0x28:
			return rlake_se;
		case 0x29:
			return rlake_sw;
		case 0x2A:
			return rshore_ne;
		case 0x2B:
			return rshore_se;
		case 0x2C:
			return rshore_nw;
		case 0x2D:
			return rshore_sw;
		case 0x2E:
			return rock;
		case 0x2F:
			return rock_ne;
		case 0x30:
			return rock_nw;
		case 0x31:
			return rock_se;
		case 0x32:
			return rock_sw;
		case 0x33:
			return rock_north;
		case 0x34:
			return rsand1;
		case 0x35:
			return rsand2;
		case 0x36:
			return rsand_east;
		case 0x37:
			return rsand_west;
		case 0x38:
			return rsand_north;
		case 0x39:
			return rsand_south;
		case 0x3A:
			return rsand_ne;
		case 0x3B:
			return rsand_nw;
		case 0x3C:
			return rsand_se;
		case 0x3D:
			return rsand_sw;
		case 0x3E:
			return rshrub;
		case 0x3F:
			return rstatue;
		case 0x40:
			return rtree_00;
		case 0x41:
			return rtree_01;
		case 0x42:
			return rtree_10;
		case 0x43:
			return rtree_20;
		case 0x44:
			return rtree_21;
		case 0x45:
			return square_rock;
		case 0x46:
			return rwaterfall;
		case 0x47:
			return stairs3;
		case 0x48:
			return stairs4;
		default:
			return null;
		}
	}

	// Enum polymorphism things
	private BufferedImage myImage;
	private int myCode;
	private boolean isSolid;

	/**
	 * Take matrix coordinates of tile and draw on g with them
	 * 
	 * @param x
	 *            matrix x
	 * @param y
	 *            matrix y
	 * @param g
	 *            the Graphics object to draw on
	 */
	public void draw(int x, int y, Graphics g) {
		int[] screenCoordinates = World.getScreenCoordinates(x, y);
		g.drawImage(myImage, screenCoordinates[0], screenCoordinates[1], null); // each
																				// tile
																				// is
																				// 16x16
	}

	/**
	 * Find out whether or not the tile is solid.
	 * 
	 * @return whether or not the tile is solid
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * Get the int representation of a tile
	 * 
	 * @return the int representation of a tile
	 */
	public int getCode() {
		return myCode;
	}
	
	public Color getPixel(int row, int col) {
		int[] thing = new int[4]; 
		myImage.getRaster().getPixel(row, col, thing);
		return new Color(thing[0], thing[1], thing[2]);
	}

	private WorldTile(String imageName, boolean solid, int byteCode) {
		try {
			myImage = ImageUtil.toBufferedImage(ImageIO.read(
					WorldTile.class.getResource("/assets/tiles/" + imageName))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE));
		} catch (IOException e) {
			System.err.println("Error reading image file: /assets/tiles/"
					+ imageName);
			System.exit(1);
		}
		isSolid = solid;
		myCode = byteCode;
	}

	public Image getImage() {
		return myImage;
	}
}
