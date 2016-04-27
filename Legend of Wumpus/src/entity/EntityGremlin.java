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
		this.x = x;
		this.y = y;
		this.sprite = gremlinImage;
	}

	@Override
	public void collide(Entity e) {
		System.out.println("gremlin collide");
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		System.out.println("gremlin damage");
	}

	@Override
	public void attack(Entity target) {
		System.out.println("Gremlin attack");
	}
}