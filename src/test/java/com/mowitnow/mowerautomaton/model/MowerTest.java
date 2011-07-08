package com.mowitnow.mowerautomaton.model;

import static com.mowitnow.mowerautomaton.model.Orientation.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;

import com.mowitnow.mowerautomaton.model.Mower;
import com.mowitnow.mowerautomaton.model.Position;

/**
 * @author Nizar Garrache
 * 
 * @since 3 juil. 2011
 */
public class MowerTest {

	@Test(expected = IllegalArgumentException.class)
	public void mower_PositionIsNull_ThrowException() {
		new Mower(null);
	}

	@Test
	public void proceedForward_IsNorthOriented_IncrementY() {
		Position position = new Position(0, 0, NORTH);
		Mower mower = new Mower(position);

		mower.proceedForward();

		assertThat(mower.getY()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsEastOriented_IncrementX() {
		Position position = new Position(0, 0, EAST);
		Mower mower = new Mower(position);

		mower.proceedForward();

		assertThat(mower.getX()).isEqualTo(1);
	}

	@Test
	public void proceedForward_IsSouthOriented_DecrementY() {
		Position position = new Position(0, 0, SOUTH);
		Mower mower = new Mower(position);

		mower.proceedForward();

		assertThat(mower.getY()).isEqualTo(-1);
	}

	@Test
	public void proceedForward_IsWestOriented_DecrementX() {
		Position position = new Position(0, 0, WEST);
		Mower mower = new Mower(position);

		mower.proceedForward();

		assertThat(mower.getX()).isEqualTo(-1);
	}

	@Test
	public void turnRight_IsNorthOriented_OrientateToEast() {
		Position position = new Position(0, 0, NORTH);
		Mower mower = new Mower(position);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(EAST);
	}

	@Test
	public void turnRight_IsEastOriented_OrientateToSouth() {
		Position position = new Position(0, 0, EAST);
		Mower mower = new Mower(position);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(SOUTH);
	}

	@Test
	public void turnRight_IsSouthOriented_OrientateToWest() {
		Position position = new Position(0, 0, SOUTH);
		Mower mower = new Mower(position);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(WEST);
	}

	@Test
	public void turnRight_IsWestOriented_OrientateToNorth() {
		Position position = new Position(0, 0, WEST);
		Mower mower = new Mower(position);

		mower.turnRight();

		assertThat(mower.getOrientation()).isEqualTo(NORTH);
	}

	@Test
	public void turnLeft_IsNorthOriented_OrientateToWest() {
		Position position = new Position(0, 0, NORTH);
		Mower mower = new Mower(position);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(WEST);
	}

	@Test
	public void turnLeft_IsWestOriented_OrientateToSouth() {
		Position position = new Position(0, 0, WEST);
		Mower mower = new Mower(position);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(SOUTH);
	}

	@Test
	public void turnLeft_IsSouthOriented_OrientateToEast() {
		Position position = new Position(0, 0, SOUTH);
		Mower mower = new Mower(position);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(EAST);
	}

	@Test
	public void turnLeft_IsEastOriented_OrientateToNorth() {
		Position position = new Position(0, 0, EAST);
		Mower mower = new Mower(position);

		mower.turnLeft();

		assertThat(mower.getOrientation()).isEqualTo(NORTH);
	}

}
