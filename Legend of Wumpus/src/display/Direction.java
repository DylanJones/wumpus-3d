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
	};

	public abstract Direction getLeft();
	public abstract Direction getRight();
}