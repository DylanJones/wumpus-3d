package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class GremlinProjectile extends EntityItem {
	protected static Image projectileImage;
	private static final int SPEED = 7;
	private int damageAmount;
	private int facing;

	static {
		//Read projecrtile images
	}

	public GremlinProjectile(int x, int y, int damageAmount, int direction) {
		//Initalize instance variables
	}

	@Override
	public void tick() {
		// Die if we fall off the screen
		// Move along the firing axis
	}

	@Override
	public void collide(Entity e) {
		//Damage enttiy if it's a player
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		// Do nothing; projectiles are immune to damage
	}

	@Override
	public void draw(Graphics g) {
		//Draw the projectile on screen
	}

}
