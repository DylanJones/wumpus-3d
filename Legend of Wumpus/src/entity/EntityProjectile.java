package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import display.Direction;
import display.World;

public class EntityProjectile extends EntityItem implements Serializable {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -9148100153596301390L;
	private static final double SPEED = 0.3;
	// This is here so it can be Serialized to disk
	private final ImageIcon projectileImageIcon;
	private Image projectileImage;
	private int damageAmount;
	private Direction facing;

	public EntityProjectile(double x, double y, int damageAmount, Direction facing2, Image image) {
		super();
		this.projectileImage = image;
		this.x = x;
		this.y = y;
		this.damageAmount = damageAmount;
		this.facing = facing2;
		this.spriteHeight = projectileImage.getHeight(null);
		this.spriteWidth = projectileImage.getWidth(null);
		this.projectileImageIcon = new ImageIcon(projectileImage);
	}
	
	
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		in.defaultReadObject();
		// Images do not serialize, we have to do it ourselves
		projectileImage = toBufferedImage(projectileImageIcon);
	}
	
	private BufferedImage toBufferedImage(ImageIcon i) {
		BufferedImage out = new BufferedImage(i.getIconWidth(), i.getIconWidth(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = out.getGraphics();
		i.paintIcon(new JPanel(), g, 0, 0);
		g.dispose();
		return out;
	}

	@Override
	public void tick() {
		// Die if we fall off the screen
		if (this.x > World.WORLD_WIDTH || this.x < 0 || this.y > World.WORLD_HEIGHT || this.y < 0)
			World.deregisterEntity(this);
		double[] coords = facing.moveInDirection(x, y, SPEED);
		x = coords[0];
		y = coords[1];
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player) {
			e.damage(damageAmount, this);
			World.deregisterEntity(this);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		int[] sCoords = World.getScreenCoordinates(x, y);
		g.drawImage(projectileImage, sCoords[0], sCoords[1], null);
	}

}
