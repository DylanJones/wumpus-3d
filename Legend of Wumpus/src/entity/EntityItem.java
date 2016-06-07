package entity;

/** Abstract superclass of all Items, Projectiles, etc. */
public abstract class EntityItem extends Entity {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = 1142312141172520401L;

	public EntityItem() {
		super();
	}

	@Override
	public void damage(int amount, Entity e) {
		// Do nothing, as Items can't take damage
	}
}