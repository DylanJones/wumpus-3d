package entity.boss;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;

import display.Angle;
import display.Tick;
import display.World;
import display.WorldTile;
import display.MusicPlayer;
import entity.Entity;
import entity.EntityProjectile;
import entity.Player;
import entity.TriforcePiece;

/** The "Null Pointer Exception" boss, the easiest one. */
public class NullPointerException extends Entity {

	/**
	 * For serialization to disk
	 */
	private static final long serialVersionUID = 99999992222222223L;
	private static Image boss1;
	private static Image boss2;
	private static Image boss3;
	private static Image boss4;
	private static Image pointerImage;
	private static Image pointerImage45;
	private static Image pointerImageNegative45;

	private Image currentImage;
	private long lastDamageTime;
	private long lastImageSwitchTime = 0;
	private long lastProjectileTime = 0;
	private boolean hasFightStarted = false;
	private boolean walkingDirection = true;

	static {
		try {
			boss1 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/boss1.png"))
					.getScaledInstance(48, 64, Image.SCALE_REPLICATE);
			boss2 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/boss2.png"))
					.getScaledInstance(48, 64, Image.SCALE_REPLICATE);
			boss3 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/boss3.png"))
					.getScaledInstance(48, 64, Image.SCALE_REPLICATE);
			boss4 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/boss4.png"))
					.getScaledInstance(48, 64, Image.SCALE_REPLICATE);
			pointerImage = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/pointer.png"))
					.getScaledInstance(18, 6, Image.SCALE_REPLICATE);
			pointerImage45 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/pointer45.png"))
					.getScaledInstance(14, 14, Image.SCALE_REPLICATE);
			pointerImageNegative45 = ImageIO
					.read(NullPointerException.class
							.getResource("/assets/nullpointerexception/pointer-45.png"))
					.getScaledInstance(14, 14, Image.SCALE_REPLICATE);
		} catch (Exception e) {
			System.err.println("Error reading NullPointerException files");
		}
	}

	public NullPointerException() {
		super();
		MusicPlayer.changePlayingMusic("/assets/music/Dungeon.wav");
		this.x = 4.0;
		this.y = 5.0;
		this.health = 10;
	}

	@Override
	public void tick() {
		if (hasFightStarted) {
			if (System.currentTimeMillis() - lastProjectileTime > 1000 + Math
					.random() * 2000) {
				new EntityProjectile(x, y, 1, Angle.EAST, pointerImage);
				new EntityProjectile(x, y, 1, Angle.SOUTHEAST,
						pointerImage45);
				new EntityProjectile(x, y, 1, Angle.NORTHEAST,
						pointerImageNegative45);
				lastProjectileTime = System.currentTimeMillis();
			}
			if (y > 8)
				walkingDirection = false;
			if (y < 3)
				walkingDirection = true;
			y += (walkingDirection) ? 0.05 : -0.05;
		} else {
			animationTick();
		}
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
			MusicPlayer.playSoundEffect("/assets/music/Boss_Scream1.wav");
			if (this.health <= 0) { // Dying
				World.deregisterEntity(this);
				MusicPlayer.playSoundEffect("/assets/music/Boss_Scream2.wav");
				MusicPlayer.changePlayingMusic("/assets/music/Overworld.wav");
				World.setTile(14, 5, WorldTile.ground);
				new TriforcePiece(World.WORLD_WIDTH / 2, World.WORLD_HEIGHT / 2);
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
			else if (this.currentImage == boss3)
				currentImage = boss4;
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
			if (World.getThePlayer().getX() > 11) {
				World.getThePlayer().move(0.05);
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
