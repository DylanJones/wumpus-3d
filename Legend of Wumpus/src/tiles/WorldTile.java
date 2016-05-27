package tiles;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum WorldTile {
	// Misc objects
	stairs1("stairs1.png", true), square_shrub("square_shrub.png", false), stairs2("stairs2.png",true), 
	round_shrub("round_shrub.png", false), statue("statue.png", false), ground("ground.png", true),
	// Tree tiles
	trees_nw("trees_nw.png", false), trees_sw("trees_sw.png", false), trees_ne("trees_ne.png", false), trees_se("trees_se.png",false),
	trees_north("trees_north.png", false), trees_south("trees_south.png", false),
	// Lake tiles
	lake_ne("lake_ne.png", false), lake_se("lake_se.png", false), lake_nw("lake_nw.png", false),
	lake_sw("lake_sw.png", false), lake_north("lake_north.png",false),
	lake_south("lake_south.png", false), lake_east("lake_east.png",false), lake_west("lake_west.png", false),
	shore_nw("shore_nw.png", true), shore_ne("shore_ne.png", true), shore_sw("shore_sw.png",true), shore_se("shore_se.png", true);
	// Methods
	private Image myImage;
	private boolean isSolid;

	public void draw(int x, int y, Graphics g) {
		g.drawImage(myImage, x, y, null);
	}

	public boolean canCollide() {
		return isSolid;
	}

	WorldTile(String imageName, boolean canCollide) {
		try {
			myImage = ImageIO.read(new File("assets/tiles/overworld/" + imageName));
		} catch (IOException e) {
			System.err.println("Error reading image file: assets/tiles/overworld/" + imageName);
			System.exit(1);
		}
		isSolid = canCollide;
	}
}
