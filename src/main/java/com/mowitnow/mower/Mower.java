package com.mowitnow.mower;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class Mower {

	private int xCoordinate;

	private int yCoordinate;
	private CardinalDirection direction;

	public Mower(int xCoordinate, int yCoordinate, CardinalDirection direction) {
		if (xCoordinate < 0 || yCoordinate < 0) {
			throw new IllegalArgumentException(
					"xCoordinate and yCoordinate should be positives");
		}
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.direction = direction;
	}

	public void proceedForward() {
		switch (direction) {
		case NORTH:
			yCoordinate++;
			break;
		case EAST:
			xCoordinate++;
			break;
		case SOUTH:
			yCoordinate--;
			break;
		case WEST:
			xCoordinate--;
			break;
		default:
			throw new AssertionError("Unknown direction [" + direction + "]");
		}
	}

	public void turnRight() {
		switch (direction) {
		case NORTH:
			direction = CardinalDirection.EAST;
			break;
		case EAST:
			direction = CardinalDirection.SOUTH;
			break;
		case SOUTH:
			direction = CardinalDirection.WEST;
			break;
		case WEST:
			direction = CardinalDirection.NORTH;
			break;
		default:
			throw new AssertionError("Unknown direction [" + direction + "]");
		}
	}

	public void turnLeft() {
		switch (direction) {
		case NORTH:
			direction = CardinalDirection.WEST;
			break;
		case WEST:
			direction = CardinalDirection.SOUTH;
			break;
		case SOUTH:
			direction = CardinalDirection.EAST;
			break;
		case EAST:
			direction = CardinalDirection.NORTH;
			break;
		default:
			throw new AssertionError("Unknown direction [" + direction + "]");
		}
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public CardinalDirection getDirection() {
		return direction;
	}

}