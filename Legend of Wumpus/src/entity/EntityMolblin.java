package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntityMolblin extends EntityMinion {
	private static Image northImage1;
	private static Image southImage1;
	private static Image eastImage1;
	private static Image westImage1;
	private static Image northImage2;
	private static Image southImage2;
	private static Image eastImage2;
	private static Image westImage2;

	static {
		// Init images
		try {
			northImage1 = ImageIO.read(
					new File("assets/molblin/molblin_north1.png"))
					.getScaledInstance(28, 32, Image.SCALE_REPLICATE);
			northImage2 = ImageIO.read(
					new File("assets/molblin/molblin_north2.png"))
					.getScaledInstance(28, 32, Image.SCALE_REPLICATE);
			southImage1 = ImageIO.read(
					new File("assets/molblin/molblin_south1.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			southImage2 = ImageIO.read(
					new File("assets/molblin/molblin_south2.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			eastImage1 = ImageIO.read(
					new File("assets/molblin/molblin_east1.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			eastImage2 = ImageIO.read(
					new File("assets/molblin/molblin_east2.png"))
					.getScaledInstance(32, 30, Image.SCALE_REPLICATE);
			westImage1 = ImageIO.read(
					new File("assets/molblin/molblin_west1.png"))
					.getScaledInstance(32, 30, Image.SCALE_REPLICATE);
			westImage2 = ImageIO.read(
					new File("assets/molblin/molblin_west2.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
		} catch (IOException e) {
			System.err.println("Error reading Molblin image files");
		}
	}

	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void damage(int amount, Entity damageSource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteWidth / 2;
		sCoords[1] -= spriteHeight / 2;
		boolean whichImage = (((int)(x * 2) % 2) == 1) ^ (((int)(y * 2) % 2) == 1);
		Image imageToDraw = null;
		switch (facing) {
		case EAST:
			if(whichImage)
				imageToDraw = eastImage1;
			else
				imageToDraw = eastImage2;
			break;
		case NORTH:
			if(whichImage)
				imageToDraw = northImage1;
			else
				imageToDraw = northImage2;
			break;
		case SOUTH:
			if(whichImage)
				imageToDraw = southImage1;
			else
				imageToDraw = southImage2;
			break;
		case WEST:
			if(whichImage)
				imageToDraw = westImage1;
			else
				imageToDraw = westImage2;
			break;
		}
		g.drawImage(imageToDraw, sCoords[0], sCoords[1], null);
	}

}
