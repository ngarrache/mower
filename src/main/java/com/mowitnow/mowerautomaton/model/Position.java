package com.mowitnow.mowerautomaton.model;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class Position {
	private int x;
	private int y;
	private Orientation orientation;

	public Position(int x, int y, Orientation orientation) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x[" + x + "] and y[" + y
					+ "] must be positives");
		}
		if (orientation == null) {
			throw new IllegalArgumentException("orientation must be not null");
		}
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public void incrementX() {
		x++;
	}

	public void decrementX() {
		x--;
	}

	public int getX() {
		return x;
	}

	public void incrementY() {
		y++;
	}

	public void decrementY() {
		y--;
	}

	public int getY() {
		return y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
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
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
