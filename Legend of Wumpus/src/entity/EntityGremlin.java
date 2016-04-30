package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import display.World;
import display.WumpusPanel;

public class EntityGremlin extends EntityMinion {
	static Image gremlinImage;
	private long lastAttackTime = 0;

	static {
		try {
			gremlinImage = ImageIO.read(new File("assets/gremlin/gremlin-icon.png")).getScaledInstance(32, 32, Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public EntityGremlin(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.health = 100;
		this.sprite = gremlinImage;
	}

	@Override
	public void collide(Entity e) {
		// System.out.println("gremlin collide");
		attack(e);
	}

	@Override
	public void damage(int amount, Entity damageSource) {
		System.out.println("gremlin damage");
		health -= amount;
	}

	@Override
	public void attack(Entity target) {
		if(System.currentTimeMillis() - lastAttackTime > 1000 && target instanceof Player) {
			lastAttackTime = System.currentTimeMillis();
			target.damage(2, this);
		}
	}

	@Override
	public void tick() {
		int playerX = World.getThePlayer().getX();
		int playerY = World.getThePlayer().getY();
		if(Math.abs(this.x - playerX) + Math.abs(this.y - playerY) < 300) {
			if(Math.abs(this.x - playerX)  > Math.abs(this.y - playerY)) {
				this.x += (x - playerX) > 1 ? -1 : 1;
			}else{
				this.y += (y - playerY) > 1 ? -1 : 1;
			}
		}
	}
}