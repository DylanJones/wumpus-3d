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
		//Read standing and jumping images
	}

	public EntitySpider(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub
		// Required by superclass

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		//Called every tick, trys to jump toward player
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		// Damage the entity e
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		// TODO Auto-generated method stub
		// Killes entity (deregisters)
	}

	@Override
	public void draw(Graphics g) {
		// Draw correct image based on jumping or standing
	}
}