package entity;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int spriteXSize;
	protected int spriteYSize;
	protected double health;
	
	public abstract void collide(Entity e);
	public abstract void damage(int amount);
}
