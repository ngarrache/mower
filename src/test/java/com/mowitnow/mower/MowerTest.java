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
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		// Act
		mower.proceedForward();

		// Assert
		assertThat(mower.getyCoordinate()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsEastOriented_IncrementX() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.EAST);

		// Act
		mower.proceedForward();

		// Assert
		assertThat(mower.getxCoordinate()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsSouthOriented_DecrementY() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		// Act
		mower.proceedForward();

		// Assert
		assertThat(mower.getyCoordinate()).isEqualTo(-1);
	}

	@Test
	public void proceedForward_IsWestOriented_DecrementX() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.WEST);

		// Act
		mower.proceedForward();

		// Assert
		assertThat(mower.getxCoordinate()).isEqualTo(-1);
	}

	@Test
	public void turnRight_IsNorthOriented_OrientateToEast() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.EAST);
	}

	@Test
	public void turnRight_IsEastOriented_OrientateToSouth() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.EAST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.SOUTH);
	}

	@Test
	public void turnRight_IsSouthOriented_OrientateToWest() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.WEST);
	}

	@Test
	public void turnRight_IsWestOriented_OrientateToNorth() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.WEST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.NORTH);
	}

	@Test
	public void turnLeft_IsNorthOriented_OrientateToWest() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.NORTH);

		// Act
		mower.turnLeft();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.WEST);
	}

	@Test
	public void turnLeft_IsWestOriented_OrientateToSouth() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.WEST);

		// Act
		mower.turnLeft();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.SOUTH);
	}

	@Test
	public void turnLeft_IsSouthOriented_OrientateToEast() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.SOUTH);

		// Act
		mower.turnLeft();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.EAST);
	}

	@Test
	public void turnLeft_IsEastOriented_OrientateToNorth() {
		// Arrange
		Mower mower = new Mower(0, 0, Orientation.EAST);

		// Act
		mower.turnLeft();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(Orientation.NORTH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void mower_XCoordinateIsNegative_ThrowException() {
		// Arrange
		// Act
		new Mower(-1, 0, Orientation.NORTH);

		// Assert
	}

	@Test(expected = IllegalArgumentException.class)
	public void mower_YCoordinateIsNegative_ThrowException() {
		// Arrange
		// Act 
		new Mower(0, -1, Orientation.NORTH);

		// Assert
	}

}
