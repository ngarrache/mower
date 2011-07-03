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
	public void turnRight_IsNorthOriented_OrientateToWest() {
		// Arrange
		Mower mower = new Mower(0, 0, CardinalDirection.NORTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.WEST);
	}

	@Test
	public void turnRight_IsEastOriented_OrientateToNorth() {
		// Arrange
		Mower mower = new Mower(0, 0, CardinalDirection.EAST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.NORTH);
	}

	@Test
	public void turnRight_IsSouthOriented_OrientateToEast() {
		// Arrange
		Mower mower = new Mower(0, 0, CardinalDirection.SOUTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.EAST);
	}

	@Test
	public void turnRight_IsWestOriented_OrientateToSouth() {
		// Arrange
		Mower mower = new Mower(0, 0, CardinalDirection.WEST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.SOUTH);
	}

}
