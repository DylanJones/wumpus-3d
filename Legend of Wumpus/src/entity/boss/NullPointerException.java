package entity.boss;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.Tick;
import display.World;
import display.WorldTile;
import entity.Entity;

/** The "Null Pointer Exception" boss. */
public class NullPointerException extends EntityBoss {

	/**
	 * For serialization to disk
	 */
	private static final long serialVersionUID = 99999992222222223L;
	private static Image boss1;
	private static Image boss2;
	private static Image boss3;
	private static Image boss4;

	private Image currentImage;
	private long lastImageSwitchTime = 0;
	private long fightStartTime;
	private boolean hasFightStarted = false;

	static {
		try {
			boss1 = ImageIO.read(new File(
					"assets/nullpointerexception/boss1.png"));
			boss2 = ImageIO.read(new File(
					"assets/nullpointerexception/boss2.png"));
			boss3 = ImageIO.read(new File(
					"assets/nullpointerexception/boss3.png"));
			boss4 = ImageIO.read(new File(
					"assets/nullpointerexception/boss4.png"));
		} catch (IOException e) {
			System.err.println("Error reading NullPointerException files");
		}
	}

	public NullPointerException() {
		super();
		this.x = 4.0;
		this.y = 5.0;
		startAnimation();
	}

	@Override
	public void tick() {
		if (hasFightStarted) {

		} else {
			animationTick();
		}
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
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteWidth / 2;
		sCoords[1] -= spriteHeight / 2;
		g.drawImage(boss1, sCoords[0], sCoords[1], null);
	}

	// ///////////FIGHT START ANIMATION///////////////
	private int animationStage = 0;
	private int lastTileSet = 0;
	private long lastActionTime;
	
	private void startAnimation() {
		fightStartTime = System.currentTimeMillis();
	}

	private void animationTick() {
		switch (animationStage) {
		case 0:
			Tick.setPlayerControl(false);
			if (World.getThePlayer().getX() > 11) {
				World.getThePlayer().move(0.15);
			} else {
				animationStage++;
				lastActionTime = System.currentTimeMillis();
			}
			break;
		case 1:
			if (System.currentTimeMillis() - lastActionTime > 500) {
				World.setTile(14, 6 - lastTileSet, WorldTile.rock);
				lastTileSet++;
				lastActionTime = System.currentTimeMillis();
			}
			if (lastTileSet > 1) {
				animationStage++;
			}
			break;
		default:
			Tick.setPlayerControl(true);
			hasFightStarted = true;
			break;
		}
	}
}
