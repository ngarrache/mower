package com.mowitnow.mower;

import static com.mowitnow.mower.Orientation.*;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class Mower {
	private Position position;

	/**
	 * @param x
	 *            positive int
	 * @param y
	 *            positive int
	 * @param orientation
	 *            must be not null
	 */
	public Mower(int x, int y, Orientation orientation) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x [" + x + "] and y [" + y
					+ "] must be positives");
		}
		if (orientation == null) {
			throw new IllegalArgumentException("orientation must be not null");
		}
		position = new Position(x, y, orientation);
	}

	public void proceedForward() {
		switch (position.getOrientation()) {
		case NORTH:
			position.incrementY();
			break;
		case EAST:
			position.incrementX();
			break;
		case SOUTH:
			position.decrementY();
			break;
		case WEST:
			position.decrementX();
			break;
		default:
			throw new AssertionError("Unknown orientation ["
					+ position.getOrientation() + "]");
		}
	}

	public void turnRight() {
		switch (position.getOrientation()) {
		case NORTH:
			position.setOrientation(EAST);
			break;
		case EAST:
			position.setOrientation(SOUTH);
			break;
		case SOUTH:
			position.setOrientation(WEST);
			break;
		case WEST:
			position.setOrientation(NORTH);
			break;
		default:
			throw new AssertionError("Unknown orientation ["
					+ position.getOrientation() + "]");
		}
	}

	public void turnLeft() {
		switch (position.getOrientation()) {
		case NORTH:
			position.setOrientation(WEST);
			break;
		case WEST:
			position.setOrientation(SOUTH);
			break;
		case SOUTH:
			position.setOrientation(EAST);
			break;
		case EAST:
			position.setOrientation(NORTH);
			break;
		default:
			throw new AssertionError("Unknown orientation ["
					+ position.getOrientation() + "]");
		}
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public Orientation getOrientation() {
		return position.getOrientation();
	}

}