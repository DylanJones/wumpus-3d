package entity.boss;

import java.awt.Image;

import display.Angle;
import display.World;
import entity.Entity;
import entity.EntityProjectile;
import entity.Player;

/**
 * An extension of Projectile, that is fired by ClassCastException. It has a
 * random chance not to deal damage.
 */
public class ClassCastProjectile extends EntityProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5351673180656581168L;

	public ClassCastProjectile(double x, double y, int damageAmount,
			Angle facing2, Image image) {
		super(x, y, damageAmount, facing2, image);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof Player && Math.random() < 0.02) {
			e.damage(damageAmount, this);
			World.deregisterEntity(this);
		}
	}

}
