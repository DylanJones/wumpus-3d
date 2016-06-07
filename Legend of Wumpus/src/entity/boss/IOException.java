package entity.boss;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

import display.MusicPlayer;
import display.World;
import entity.Entity;
import entity.Player;
import entity.TriforcePiece;

/** The IOException boss, the medium difficulty one */
public class IOException extends Entity {

	/**
	 * For serialization to disk
	 */
	private static final long serialVersionUID = 748984645444456661L;
	private static final double SPEED = 0.05;
	private static Image boss1;
	private static Image boss2;

	private long lastDamageTime;
	private long lastImageSwitchTime;
	private Image currentImage;

	static {
		try {
			boss1 = ImageIO.read(IOException.class.getResource("/assets/ioexception/boss1.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
			boss2 = ImageIO.read(IOException.class.getResource("/assets/ioexception/boss2.png"))
					.getScaledInstance(32, 32, Image.SCALE_REPLICATE);
		} catch (Exception e) {
			System.err.println("Error reading IOException image files!");
			System.exit(1);
		}
	}

	public IOException() {
		super();
		MusicPlayer.changePlayingMusic("/assets/music/Dungeon.wav");
		this.x = 7.5;
		this.y = 4.0;
		this.health = 20;
	}

	@Override
	public void tick() {
		if (Math.random() < 0.01)
			new IOMinion(x, y);
		Player p = World.getThePlayer(); // For convince
		if (p.getX() > this.x)
			this.x += SPEED;
		else if (p.getX() < this.x)
			this.x -= SPEED;
		if (p.getY() > this.x)
			this.y += SPEED;
		else if (p.getY() < this.y)
			this.y -= SPEED;
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
			else
				currentImage = boss1;
			setHitbox(currentImage);
		}
		g.drawImage(currentImage, sCoords[0], sCoords[1], null);
	}
}
