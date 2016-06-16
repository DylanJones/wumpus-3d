package display;

import java.io.Serializable;

/**
 * Class that describes the direction in which an entity is facing. Has a
 * moveInDirection method for convenience.
 */
public class Angle implements Serializable {
	/*
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = 33544444446765742L;
	// The angle in degrees
	private double degrees;
	
	public enum CardinalDirection {
		 NORTH, SOUTH, EAST, WEST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST
	}

	public Angle(double angleDegrees) {
		this.add(angleDegrees);
	}

	public Angle(Angle a) {
		this.add(a.degrees);;
	}

	public CardinalDirection toCardinalDirection() {
		double tmp = degrees / 45.0;
		int dir = (int) Math.round(tmp) % 8;
		switch(dir) {
		case 0:
			return CardinalDirection.NORTH;
		case 1:
			return CardinalDirection.NORTHEAST;
		case 2:
			return CardinalDirection.EAST;
		case 3:
			return CardinalDirection.SOUTHEAST;
		case 4:
			return CardinalDirection.SOUTH;
		case 5:
			return CardinalDirection.SOUTHWEST;
		case 6:
			return CardinalDirection.WEST;
		case 7:
			return CardinalDirection.NORTHWEST;
		default:
			System.err.println("Invalid angle " + degrees);
			return CardinalDirection.NORTH;
		}
	}

	/**
	 * Add amount to the current degrees.
	 * 
	 * @param amount
	 *            how many degrees to add
	 */
	public void add(double amount) {
		this.degrees += amount;
		this.degrees %= 360;
		if (this.degrees < 0)
			this.degrees += 360;
	}

	/**
	 * Move in the direction and return the new coordinates.
	 * 
	 * @param x
	 *            the starting x coord
	 * @param y
	 *            the starting y coord
	 * @param movement
	 *            how far to move
	 * @return the new coordinates after moving
	 */
	public double[] moveInDirection(double x, double y, double movement) {
		// Use trigonometry to find the length moved in x and y
		double horizontalLength = movement * Math.sin(Math.toRadians(degrees));
		double verticalLength = movement * Math.cos(Math.toRadians(degrees));
		return new double[] { x + horizontalLength, y - verticalLength };
	}

	public double getDegrees() {
		return degrees;
	}
	
	@Override
	public String toString() {
		return degrees + " degrees, cardinal " + this.toCardinalDirection();
	}
}