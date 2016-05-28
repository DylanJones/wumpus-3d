package display;

public enum Direction {
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
			return new double[] { x - movement, y };
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
			return new double[] { x + movement, y };
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
			// south directions.
			System.out.println("Not sure if diagonal enums work");
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
			// Use pythagorean theorem to find the length moved in north and
			// south directions.
			System.out.println("Not sure if diagonal enums work");
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
			// Use pythagorean theorem to find the length moved in north and
			// south directions.
			System.out.println("Not sure if diagonal enums work");
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
			// Use pythagorean theorem to find the length moved in north and
			// south directions.
			System.out.println("Not sure if diagonal enums work");
			double sideLength = movement * Math.sin(Math.toRadians(45));
			return new double[] { EAST.moveInDirection(x, y, sideLength)[0],
					SOUTH.moveInDirection(x, y, sideLength)[1] };
		}
	};

	public abstract double[] moveInDirection(double x, double y, double movement);

	public abstract Direction getLeft();

	public abstract Direction getRight();
}