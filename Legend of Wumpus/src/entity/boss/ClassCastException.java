package entity.boss;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.Direction;
import display.MusicPlayer;
import display.Tick;
import display.World;
import display.WorldTile;
import entity.Entity;
import entity.EntityProjectile;
import entity.Player;

public class ClassCastException extends EntityBoss {

	/**
	 * For serialization to disk
	 */
	private static final long serialVersionUID = 748984645444456661L;
	private static final double SPEED = 0.05;
	private static Image boss1;
	private static Image boss2;
	private static Image boss3;
	private static Image projectileImage;

	private boolean scared = true;
	private long lastScaredSwitchTime;
	private boolean hasFightStarted = false;
	private long lastDamageTime;
	private long lastImageSwitchTime;
	private Image currentImage;

	static {
		try {
			boss1 = ImageIO.read(
					new File("assets/classcastexception/boss1.png"))
					.getScaledInstance(48, 76, Image.SCALE_REPLICATE);
			boss2 = ImageIO.read(
					new File("assets/classcastexception/boss2.png"))
					.getScaledInstance(48, 72, Image.SCALE_REPLICATE);
			boss3 = ImageIO.read(
					new File("assets/classcastexception/boss3.png"))
					.getScaledInstance(48, 76, Image.SCALE_REPLICATE);
			projectileImage = ImageIO.read(new File(
					"assets/classcastexception/projectile.png"));
		} catch (IOException e) {
			System.err.println("Error reading ClassCastException image files!");
			System.exit(1);
		}
	}

	public ClassCastException() {
		super();
		MusicPlayer.changePlayingMusic("assets/music/Dungeon.wav");
		this.x = 7.5;
		this.y = 4.0;
		this.health = 20;
	}

	@Override
	public void tick() {
		if (hasFightStarted) {
			Player p = World.getThePlayer(); // For convince
			if (scared) {
				shootAllDirections();
				if (p.getX() > this.x
						&& !World.getTileAt(x - SPEED, y).isSolid())
					this.x -= SPEED;
				else if (p.getX() < this.x
						&& !World.getTileAt(x + SPEED, y).isSolid())
					this.x += SPEED;
				if (p.getY() > this.x
						&& !World.getTileAt(x, y - SPEED).isSolid())
					this.y -= SPEED;
				else if (p.getY() < this.y
						&& !World.getTileAt(x, y + SPEED).isSolid())
					this.y += SPEED;
			} else {
				if (p.getX() > this.x)
					this.x += SPEED;
				else if (p.getX() < this.x)
					this.x -= SPEED;
				if (p.getY() > this.x)
					this.y += SPEED;
				else if (p.getY() < this.y)
					this.y -= SPEED;
			}
			if (System.currentTimeMillis() - this.lastScaredSwitchTime > 2000 + Math
					.random() * 6000) {
				lastScaredSwitchTime = System.currentTimeMillis();
				scared = !scared;
			}
		} else {
			animationTick();
		}
	}

	private void shootAllDirections() {
		new ClassCastProjectile(x, y, 1, Direction.NORTH, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.SOUTH, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.EAST, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.WEST, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.NORTHEAST, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.NORTHWEST, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.SOUTHEAST, projectileImage);
		new ClassCastProjectile(x, y, 1, Direction.SOUTHWEST, projectileImage);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player)
			e.damage(2, this);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		if (System.currentTimeMillis() - lastDamageTime > 1000) {
			lastDamageTime = System.currentTimeMillis();
			this.health -= amount;
			System.out.println(health);
			MusicPlayer.playSoundEffect("assets/music/Boss_Scream1.wav");
			if (this.health <= 0) { // Dying
				World.deregisterEntity(this);
				MusicPlayer.playSoundEffect("assets/music/Boss_Scream2.wav");
				MusicPlayer.changePlayingMusic("assets/music/Overworld.wav");
				World.setTile(7, 9, WorldTile.stairs2);
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		sCoords[0] -= spriteWidth / 2;
		sCoords[1] -= spriteHeight / 2;
		if (System.currentTimeMillis() - this.lastImageSwitchTime > 250) {
			lastImageSwitchTime = System.currentTimeMillis();
			if (this.currentImage == boss1)
				currentImage = boss2;
			else if (this.currentImage == boss2)
				currentImage = boss3;
			else
				currentImage = boss1;
			setHitbox(currentImage);
		}
		g.drawImage(currentImage, sCoords[0], sCoords[1], null);
	}

	// ///////////FIGHT START ANIMATION///////////////
	private int animationStage = 0;
	private int lastTileSet = 0;
	private long lastActionTime;

	private void animationTick() {
		switch (animationStage) {
		case 0:
			Tick.setPlayerControl(false);
			if (World.getThePlayer().getY() > 8) {
				World.getThePlayer().move(0.05);
			} else {
				animationStage++;
				lastActionTime = System.currentTimeMillis();
			}
			break;
		case 1:
			if (System.currentTimeMillis() - lastActionTime > 500) {
				World.setTile(7, 9, WorldTile.trees_north);
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
