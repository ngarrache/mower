package com.mowitnow.mower;

import static org.fest.assertions.Assertions.*;

import org.junit.Before;
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
		Mower mower = new Mower(CardinalDirection.NORTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.WEST);
	}

	@Test
	public void turnRight_IsEastOriented_OrientateToNorth() {
		// Arrange
		Mower mower = new Mower(CardinalDirection.EAST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.NORTH);
	}

	@Test
	public void turnRight_IsSouthOriented_OrientateToEast() {
		// Arrange
		Mower mower = new Mower(CardinalDirection.SOUTH);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.EAST);
	}

	@Test 
	public void turnRight_IsWestOriented_OrientateToSouth() {
		// Arrange
		Mower mower = new Mower(CardinalDirection.WEST);

		// Act
		mower.turnRight();

		// Assert
		assertThat(mower.getDirection()).isEqualTo(CardinalDirection.SOUTH);
	} 

}
