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

	public Angle(double angleDegrees) {
		degrees = angleDegrees;
	}

	public Angle add(Angle a) {
		return new Angle((degrees + a.degrees) % 360);
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
		double verticalLength = movement * Math.sin(Math.toRadians(degrees));
		double horizontalLength = movement * Math.cos(Math.toRadians(degrees));
		return new double[] { x + horizontalLength, y + verticalLength };
	}
}