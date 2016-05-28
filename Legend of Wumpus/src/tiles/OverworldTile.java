package tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum OverworldTile implements WorldTile{
	// Misc objects
	stairs1("stairs1.png", true, 0x01), square_shrub("square_shrub.png", false, 0x01), stairs2("stairs2.png", true, 0x02), 
	round_shrub("round_shrub.png", false, 0x03), statue("statue.png", false, 0x04), ground("ground.png", true, 0x05),
	// Tree tiles
	trees_nw("trees_nw.png", false, 0x06), trees_sw("trees_sw.png", false, 0x07), trees_ne("trees_ne.png", false, 0x08), trees_se("trees_se.png",false, 0x09),
	trees_north("trees_north.png", false, 0x0A), trees_south("trees_south.png", false, 0x0B),
	// Lake tiles
	lake_ne("lake_ne.png", false, 0x0C), lake_se("lake_se.png", false, 0x0D), lake_nw("lake_nw.png", false, 0x0E),
	lake_sw("lake_sw.png", false, 0x0F), lake_north("lake_north.png",false, 0x10),
	lake_south("lake_south.png", false, 0x11), lake_east("lake_east.png",false, 0x12), lake_west("lake_west.png", false, 0x13),
	shore_nw("shore_nw.png", true, 0x14), shore_ne("shore_ne.png", true, 0x15), shore_sw("shore_sw.png",true, 0x16), shore_se("shore_se.png", true, 0x17);
	// Methods
	private Image myImage;
	private int myCode;
	private boolean isSolid;

	public void draw(int x, int y, Graphics g) {
		g.drawImage(myImage, x, y, null);
	}

	public boolean canCollide() {
		return isSolid;
	}

	OverworldTile(String imageName, boolean canCollide, int byteCode) {
		try {
			myImage = ImageIO.read(new File("assets/tiles/overworld/" + imageName));
		} catch (IOException e) {
			System.err.println("Error reading image file: assets/tiles/overworld/" + imageName);
			System.exit(1);
		}
		isSolid = canCollide;
		this.myCode = byteCode;
	}

	@Override
	public int getCode() {
		return myCode;
	}
}
