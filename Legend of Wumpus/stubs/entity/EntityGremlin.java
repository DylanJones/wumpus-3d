package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import display.World;

public class EntityGremlin extends EntityMinion {
	//Resource Files
	private static Image gremlinNorth;
	private static Image gremlinSouth;
	private static Image gremlinEast;
	private static Image gremlinWest;
	private int facing = World.NORTH;
	// For its walking square
	private int squareX1 = 0;
	private int squareY1 = 0;
	private int squareX2 = 0;
	private int squareY2 = 0;
	private boolean clockwise;

	static {
	//Read gremlin images
	}

	// Gremlins walk in squares
	public EntityGremlin(int squareX1, int squareY1, int squareX2, int squareY2) {
		super();
		// Intialize instance variables
	}

	@Override
	public void collide(Entity e) {
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		//Subtract from health
		// Deregister (kill) if health goes below 0
	}

	@Override
	public void attack(Entity target) {
		//If target is player: damage
	}

	@Override
	public void tick() {
		//Random chance to sgoot projectile
		//Walk along square
	}

	@Override
	public void draw(Graphics g) {
		//Draw correct image on screen
	}
}