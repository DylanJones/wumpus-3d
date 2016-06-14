package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import display.Angle;
import display.ImageUtil;
import display.World;
import display.MusicPlayer;

/** Another enemy that lives in the world. It has 3 health and shoots arrows. */
public class EntityMolblin extends EntityMinion {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -8833066142798127669L;
	private static Image northImage1;
	private static Image southImage1;
	private static Image eastImage1;
	private static Image westImage1;
	private static Image northImage2;
	private static Image southImage2;
	private static Image eastImage2;
	private static Image westImage2;

	private static final double SPEED = 0.1;
	private static BufferedImage arrowImage;

	static {
		// Initialize images
		try {
			northImage1 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_north1.png"))
					.getScaledInstance(28, 32, Image.SCALE_REPLICATE);
			northImage2 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_north2.png"))
					.getScaledInstance(28, 32, Image.SCALE_REPLICATE);
			southImage1 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_south1.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			southImage2 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_south2.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			eastImage1 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_east1.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			eastImage2 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_east2.png"))
					.getScaledInstance(32, 30, Image.SCALE_REPLICATE);
			westImage1 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_west1.png"))
					.getScaledInstance(32, 30, Image.SCALE_REPLICATE);
			westImage2 = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/molblin_west2.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			Image arrowImageTemp = ImageIO.read(
					EntityMolblin.class
							.getResource("/assets/molblin/arrow.png"))
					.getScaledInstance(10, 32, Image.SCALE_REPLICATE);
			// Convert to BufferedImage
			// Create a buffered image with transparency
			arrowImage = new BufferedImage(arrowImageTemp.getWidth(null),
					arrowImageTemp.getHeight(null), BufferedImage.TYPE_INT_ARGB);

			// Draw the image on to the buffered image
			Graphics2D bGr = arrowImage.createGraphics();
			bGr.drawImage(arrowImageTemp, 0, 0, null);
			bGr.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error reading Molblin image files");
			System.exit(1);
		}
	}

	public EntityMolblin(double x, double y) {
		this.x = x;
		this.y = y;
		this.health = 3; // 3 hits w/ wooden sword
		this.facing = new Angle(0);
		this.setHitbox(northImage1);
	}

	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub
	}

	private void shoot() {
		BufferedImage rotated = arrowImage;
		switch (facing.toCardinalDirection()) {
		case WEST:
			rotated = ImageUtil.rotate(arrowImage, 270);
			break;
		case SOUTH:
			rotated = ImageUtil.rotate(rotated, 180);
			break;
		case EAST:
			rotated = ImageUtil.rotate(rotated, 90);
			break;
		case NORTH:
			rotated = arrowImage;
			break;
		default:
			rotated = arrowImage;
			break;
		}
		new EntityProjectile(x, y, 1, facing, rotated);
		MusicPlayer.playSoundEffect("/assets/music/Arrow.wav");
	}

	@Override
	public void tick() {
		// Shoot player
		if (Math.random() < 0.02) {
			shoot();
		}
		// Randomly turn
		if (Math.random() < 0.01)
			randomTurn();
		// Move
		if (World.willCollideTile(this, SPEED)) {
			randomTurn();
		} else { // Turn
			double[] newCoords = facing.moveInDirection(x, y, SPEED);
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	private void randomTurn() {
		if (Math.random() < 0.5)
			facing.add(90);
		else
			facing.add(-90);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player)
			e.damage(1, this);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		health -= amount;
		if (health <= 0) {
			World.deregisterEntity(this);
			MusicPlayer.playSoundEffect("/assets/music/Kill.wav");
			if (Math.random() < 0.9) {
				new EntityHeart(x, y, 2);
			}
		} else {
			MusicPlayer.playSoundEffect("/assets/music/Hit.wav");
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteWidth / 2;
		sCoords[1] -= spriteHeight / 2;
		boolean whichImage = (((int) (x * 2) % 2) == 1)
				^ (((int) (y * 2) % 2) == 1);
		Image imageToDraw = null;
		switch (facing.toCardinalDirection()) {
		case EAST:
			if (whichImage)
				imageToDraw = eastImage1;
			else
				imageToDraw = eastImage2;
			break;
		case NORTH:
			if (whichImage)
				imageToDraw = northImage1;
			else
				imageToDraw = northImage2;
			break;
		case SOUTH:
			if (whichImage)
				imageToDraw = southImage1;
			else
				imageToDraw = southImage2;
			break;
		case WEST:
			if (whichImage)
				imageToDraw = westImage1;
			else
				imageToDraw = westImage2;
			break;
		}
		g.drawImage(imageToDraw, sCoords[0], sCoords[1], null);
		setHitbox(imageToDraw);
	}
}
