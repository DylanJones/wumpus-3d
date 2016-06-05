package entity;

/** This class is the superclass for all hostile living things. */
public abstract class EntityMinion extends Entity {
	/**
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = -2048816544234580612L;

	public abstract void attack(Entity target);

	public EntityMinion() {
		super();
	}
}