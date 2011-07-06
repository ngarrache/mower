package com.mowitnow.mower;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

/**
 * @author Nizar Garrache
 * 
 * @since 4 juil. 2011
 */
public class MowersControllerTest {

	private static final int MAX_X_COORDINATE = 5;
	private static final int MAX_Y_COORDINATE = 5;
	private static final int NEGATIVE_MAX_X_COORDINATE = -5;
	private static final int NEGATIVE_MAX_Y_COORDINATE = -5;
	private static final int X_COORDINATE = 1;
	private static final int Y_COORDINATE = 2;

	private MowersController controller;

	@Before
	public void setUp() {
		controller = new MowersController();
	}

	@Test
	public void initLawn_Ok_SetLawnSize() {
		// Arrange
		// Act
		controller.initLawn(MAX_X_COORDINATE, MAX_Y_COORDINATE);

		// Assert
		assertThat(controller.getMaxXCoordinate()).isEqualTo(MAX_X_COORDINATE);
		assertThat(controller.getMaxYCoordinate()).isEqualTo(MAX_Y_COORDINATE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void initLawn_MaxXIsNegative_ThrowException() {
		// Arrange
		// Act
		controller.initLawn(NEGATIVE_MAX_X_COORDINATE, MAX_Y_COORDINATE);

		// Assert
	}

	@Test(expected = IllegalArgumentException.class)
	public void initLawn_MaxYIsNegative_ThrowException() {
		// Arrange
		// Act
		controller.initLawn(MAX_X_COORDINATE, NEGATIVE_MAX_Y_COORDINATE);

		// Assert
	}

	@Test
	public void deployMower_Ok_InitNewMower() {
		// Arrange
		// Act
		controller.deployMower(X_COORDINATE, Y_COORDINATE,
				CardinalDirection.NORTH);

		// Assert
		assertThat(controller.getMowers()).hasSize(1);
		assertThat(controller.getMowers()).onProperty("xCoordinate")
				.containsExactly(X_COORDINATE);
		assertThat(controller.getMowers()).onProperty("yCoordinate")
				.containsExactly(Y_COORDINATE);
		assertThat(controller.getMowers()).onProperty("direction")
				.containsExactly(CardinalDirection.NORTH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deployMower_XIsNegative_ThrowException() {
		// Arrange
		// Act
		controller.deployMower(NEGATIVE_MAX_X_COORDINATE, Y_COORDINATE,
				CardinalDirection.NORTH);
		// Assert
	}

	@Test
	public void runMower_RFL_MoveMower() {
		// Arrange
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);
		Mower mowerMock = mock(Mower.class);
		when(mowerMock.getxCoordinate()).thenReturn(1);
		when(mowerMock.getyCoordinate()).thenReturn(0);
		when(mowerMock.getDirection()).thenReturn(CardinalDirection.NORTH);

		// Act
		controller.runMower(mowerMock, instructions);

		// Assert
		InOrder inOrder = inOrder(mowerMock);
		inOrder.verify(mowerMock).turnRight();
		inOrder.verify(mowerMock).proceedForward();
		inOrder.verify(mowerMock).turnLeft();
	}
	
	@Test
	public void doWork_Ok_DeployAndRunMowers() {
		//Arrange
		
		//Act

		//Assert
		

	}

}
