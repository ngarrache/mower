package com.mowitnow.mower;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class Mower {

	private CardinalDirection direction;

	public Mower(int x, int y, CardinalDirection direction) {
		this.direction = direction;
	}

	public void turnRight() {
		if (direction == CardinalDirection.NORTH) {
			direction = CardinalDirection.WEST;
		} else if (direction == CardinalDirection.EAST) {
			direction = CardinalDirection.NORTH;
		} else if (direction == CardinalDirection.SOUTH) {
			direction = CardinalDirection.EAST;
		} else if (direction == CardinalDirection.WEST) {
			direction = CardinalDirection.SOUTH;
		}

	}

	public CardinalDirection getDirection() {
		return direction;
	}

}
