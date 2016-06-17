package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import entity.Entity;

public class TileRenderer {
	public static void renderTiles(Graphics2D g) {
		// renderTiles2D(g);
		g.setColor(Color.RED);
		// 45 degrees to either side of player
		double playerDegrees = World.getThePlayer().getFacing().getDegrees();
		Angle angle = new Angle(playerDegrees - 45);
		// One column for each horizontal pixel
		for (int i = 0; i < World.WORLD_WIDTH * 32; i++) {
			drawColumn(angle, g, i);
			angle.add(90 / (double)(World.WORLD_WIDTH * 32));
		}
	}

	private static void drawColumn(Angle a, Graphics2D g, int col) {
		Tracer t = new Tracer(a);
		double distance = t.trace();
		if (distance < 1)
			distance = 1;
		int height = (int) (1.0 / distance * World.WORLD_HEIGHT * 32);
		int y1 = (World.WORLD_HEIGHT * 32 - height) / 2 + 112;
		int y2 = y1 + height;
		if (t.getX() >= World.WORLD_WIDTH || t.getX() <= 0
				|| t.getY() >= World.WORLD_HEIGHT || t.getY() <= 0) {
			g.setColor(Color.CYAN);
			g.drawLine(col, y1, col, y2);
		} else {
			g.setColor(Color.red);
			WorldTile tile = World.getTileAt(t.getX(), t.getY());
			for (int row = y1; row < y2; row++) {
				g.setColor(tile.getPixel((int)(Math.random() * 16), (int)((row - y1) / (double)height)));
				g.fillRect(col, row, 1, 1);
			}
		}
		// Draw a line
		// double[] nCoords = a.moveInDirection(World.getThePlayer().getX(),
		// World
		// .getThePlayer().getY(), distance);
		// int[] pCoords =
		// World.getScreenCoordinates(World.getThePlayer().getX(),
		// World.getThePlayer().getY());
		// int[] lCoords = World.getScreenCoordinates(nCoords[0], nCoords[1]);
		// Image theImage = World.getTileAt(t.getX(), t.getY()).getImage();
		// // theImage.getGraphics();
		// g.setColor(Color.DARK_GRAY);
		// g.drawLine(pCoords[0], pCoords[1], lCoords[0], lCoords[1]);
	}

	public static void renderTiles2D(Graphics g) {
		for (int x = 0; x < World.WORLD_WIDTH; x++) {
			for (int y = 0; y < World.WORLD_HEIGHT; y++) {
				World.getTileAt(x, y).draw(x, y, g);
			}
		}
	}

	private static class Tracer extends Entity {
		private static final long serialVersionUID = -6034222719679039812L;
		public double distanceTraveled = 0;
		public static final double PRECISION = 0.05;

		public Tracer(Angle facing) {
			// do NOT call super(); we don't want to be registered as an entity
			this.x = World.getThePlayer().getX();
			this.y = World.getThePlayer().getY();
			this.facing = facing;
			this.spriteWidth = 1; // So that we have a hitbox
			this.spriteHeight = 1;
			// Don't put us on the entity list! If we are put there, it will
			// quickly fill up.
			World.deregisterEntity(this);
		}

		public double trace() {
			while (!World.willCollideTile(this, PRECISION)) {
				double[] nCoords = facing.moveInDirection(x, y, PRECISION);
				x = nCoords[0];
				y = nCoords[1];
				distanceTraveled += PRECISION;
			} // Move one more time so that we are inside the block
			double[] nCoords = facing.moveInDirection(x, y, PRECISION * 2);
			x = nCoords[0];
			y = nCoords[1];
			return distanceTraveled;
		}

		public void tick() {
		}

		public void collide(Entity e) {
		}

		public void damage(int amount, Entity damageSource) {
		}

		public void draw(Graphics2D g) {
		}
	}
}
