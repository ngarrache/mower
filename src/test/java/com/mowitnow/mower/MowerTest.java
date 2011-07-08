package com.mowitnow.mower;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class MowerTest {

	@Test
	public void proceedForward_IsNorthOriented_IncrementY() {
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		mower.proceedForward();

		assertThat(mower.getY()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsEastOriented_IncrementX() {
		Mower mower = new Mower(0, 0, Orientation.EAST);

		mower.proceedForward();

		assertThat(mower.getX()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsSouthOriented_DecrementY() {
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		mower.proceedForward();

		assertThat(mower.getY()).isEqualTo(-1);
	}

	@Test
	public void proceedForward_IsWestOriented_DecrementX() {
		Mower mower = new Mower(0, 0, Orientation.WEST);

		mower.proceedForward();

		assertThat(mower.getX()).isEqualTo(-1);
	}

	@Test
	public void turnRight_IsNorthOriented_OrientateToEast() {
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
	}

	@Test
	public void turnRight_IsEastOriented_OrientateToSouth() {
		Mower mower = new Mower(0, 0, Orientation.EAST);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
	}

	@Test
	public void turnRight_IsSouthOriented_OrientateToWest() {
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
	}

	@Test
	public void turnRight_IsWestOriented_OrientateToNorth() {
		Mower mower = new Mower(0, 0, Orientation.WEST);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
	}

	@Test
	public void turnLeft_IsNorthOriented_OrientateToWest() {
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
	}

	@Test
	public void turnLeft_IsWestOriented_OrientateToSouth() {
		Mower mower = new Mower(0, 0, Orientation.WEST);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
	}

	@Test
	public void turnLeft_IsSouthOriented_OrientateToEast() {
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
	}

	@Test
	public void turnLeft_IsEastOriented_OrientateToNorth() {
		Mower mower = new Mower(0, 0, Orientation.EAST);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void mower_XCoordinateIsNegative_ThrowException() {
		new Mower(-1, 0, Orientation.NORTH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void mower_YCoordinateIsNegative_ThrowException() {
		new Mower(0, -1, Orientation.NORTH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void mower_OrientationIsNull_ThrowException() {
		new Mower(0, 0, null);
	}

}
