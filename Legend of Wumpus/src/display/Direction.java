package display;

import java.io.Serializable;

/**
 * Simple enum for direction of an entity. Provides methods to move in a
 * specified direction.
 */
public enum Direction implements Serializable {
	NORTH {
		@Override
		public Direction getLeft() {
			return WEST;
		}

		@Override
		public Direction getRight() {
			return EAST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			return new double[] { x, y - movement };
		}
	},
	SOUTH {
		@Override
		public Direction getLeft() {
			return EAST;
		}

		@Override
		public Direction getRight() {
			return WEST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			return new double[] { x, y + movement };
		}
	},
	EAST {
		@Override
		public Direction getLeft() {
			return NORTH;
		}

		@Override
		public Direction getRight() {
			return SOUTH;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			return new double[] { x + movement, y };
		}
	},
	WEST {
		@Override
		public Direction getLeft() {
			return SOUTH;
		}

		@Override
		public Direction getRight() {
			return NORTH;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			return new double[] { x - movement, y };
		}
	},
	NORTHWEST {
		@Override
		public Direction getLeft() {
			return SOUTHWEST;
		}

		@Override
		public Direction getRight() {
			return NORTHEAST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			// Use pythagorean theorem to find the length moved in north and
			// west directions.
			double sideLength = movement * Math.sin(Math.toRadians(45));
			return new double[] { WEST.moveInDirection(x, y, sideLength)[0],
					NORTH.moveInDirection(x, y, sideLength)[1] };
		}
	},
	SOUTHWEST {
		@Override
		public Direction getLeft() {
			return SOUTHEAST;
		}

		@Override
		public Direction getRight() {
			return NORTHWEST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			// Use trigonometry to find the length moved in south and
			// west directions.
			double sideLength = movement * Math.sin(Math.toRadians(45));
			return new double[] { WEST.moveInDirection(x, y, sideLength)[0],
					SOUTH.moveInDirection(x, y, sideLength)[1] };
		}
	},
	NORTHEAST {
		@Override
		public Direction getLeft() {
			return NORTHWEST;
		}

		@Override
		public Direction getRight() {
			return SOUTHEAST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			// Use trigonometry to find the length moved in north and
			// east directions.
			double sideLength = movement * Math.sin(Math.toRadians(45));
			return new double[] { EAST.moveInDirection(x, y, sideLength)[0],
					NORTH.moveInDirection(x, y, sideLength)[1] };
		}
	},
	SOUTHEAST {
		@Override
		public Direction getLeft() {
			return NORTHEAST;
		}

		@Override
		public Direction getRight() {
			return SOUTHWEST;
		}

		@Override
		public double[] moveInDirection(double x, double y, double movement) {
			// Use trigonometry to find the length moved in east and
			// south directions.
			double sideLength = movement * Math.sin(Math.toRadians(45));
			return new double[] { EAST.moveInDirection(x, y, sideLength)[0],
					SOUTH.moveInDirection(x, y, sideLength)[1] };
		}
	};

	/*
	 * Serial ID for Serialization to disk
	 */
	private static final long serialVersionUID = 33544444444435342L;

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
	public abstract double[] moveInDirection(double x, double y, double movement);

	/**
	 * Get the direction to the left of this one (NORTH's left is WEST, WEST's
	 * left is SOUTH, etc.
	 * 
	 * @return the Direction to the left
	 */
	public abstract Direction getLeft();

	/**
	 * Get the direction to the right of this one (NORTH's right is EAST, EAST's
	 * right is SOUTH, etc.
	 * 
	 * @return the Direction to the right
	 */
	public abstract Direction getRight();
}