package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EntitySpider extends EntityMinion {
	private static Image standingImage;
	private static Image jumpingImage;
	
	static {
		try {
			standingImage = ImageIO.read(new File("assets/spider/standing.png"));
			jumpingImage = ImageIO.read(new File("assets/spider/jumping.png"));
		} catch (IOException e) {
			System.err.println("Error reading Spider images!");
			System.exit(1);
		}
	}

	public EntitySpider(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
		// TODO Auto-generated method stub

	}

}