package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EntityGremlin extends EntityMinion {
	static Image gremlinImage;

	static {
		try {
			gremlinImage = ImageIO.read(new File("assets/gremlin/gremlin-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public EntityGremlin(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);
		this.health = 100;
		this.sprite = gremlinImage;
	}

	@Override
	public void collide(Entity e) {
//		System.out.println("gremlin collide");
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		System.out.println("gremlin damage");
		health -= amount;
	}

	@Override
	public void attack(Entity target) {
//		System.out.println("Gremlin attack");
		target.damage(2, this);
	}
}