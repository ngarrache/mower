package com.mowitnow.mower;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class PositionAndOrientation {
	private int x;
	private int y;
	private Orientation orientation;

	public PositionAndOrientation(int x, int y, Orientation orientation) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x[" + x + "] and y[" + y
					+ "] must be positives");
		}
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
