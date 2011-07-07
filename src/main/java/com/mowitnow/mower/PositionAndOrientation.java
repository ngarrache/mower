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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionAndOrientation other = (PositionAndOrientation) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
