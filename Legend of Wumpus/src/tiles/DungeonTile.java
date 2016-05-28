package tiles;

import java.awt.Graphics;
import java.awt.Image;

public enum DungeonTile implements WorldTile {
	;

	private int myCode;
	private boolean canCollide;
	private Image myImage;

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(int x, int y, Graphics g) {

	}

}
