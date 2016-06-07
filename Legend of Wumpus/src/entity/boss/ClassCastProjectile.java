package entity.boss;

import java.awt.Image;

import display.Direction;
import entity.EntityProjectile;

public class ClassCastProjectile extends EntityProjectile {

	public ClassCastProjectile(double x, double y, int damageAmount,
			Direction facing2, Image image) {
		super(x, y, damageAmount, facing2, image);
	}

}
