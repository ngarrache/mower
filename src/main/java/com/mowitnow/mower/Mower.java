package com.mowitnow.mower;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class Mower {

	private CardinalDirection direction;

	public Mower(CardinalDirection initialDirection) {
		this.direction = initialDirection;
	}

	public void turnRight() {
		switch (direction) {
		case NORTH:
			direction = CardinalDirection.WEST;
			break;
		case EAST:
			direction = CardinalDirection.NORTH;
			break;
		case SOUTH:
			direction = CardinalDirection.EAST;
			break;
		case WEST:
			direction = CardinalDirection.SOUTH;
			break;
		default:
			throw new AssertionError("Unknown direction [" + direction + "]");
		}
	}

	public CardinalDirection getDirection() {
		return direction;
	}

}
