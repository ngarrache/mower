package com.mowitnow.mower;

import static com.mowitnow.mower.Orientation.*;
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
public class ControllerTest {

	private Controller controller;

	@Before
	public void setUp() {
		controller = new Controller();
	}

	@Test
	public void deployMower_Ok_InitNewMower() {
		Position position = new Position(0, 0, NORTH);

		controller.deployMower(position);

		assertThat(controller.getMowers()).onProperty("position")
				.containsExactly(position);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deployMower_XIsNegative_ThrowException() {
		Position position = new Position(-1, 0, NORTH);

		controller.deployMower(position);
	}

	@Test
	public void runMower_RFL_MoveMower() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);
		Mower mowerMock = mock(Mower.class);
		when(mowerMock.getX()).thenReturn(1);
		when(mowerMock.getY()).thenReturn(0);
		when(mowerMock.getOrientation()).thenReturn(Orientation.NORTH);

		controller.runMower(mowerMock, instructions);

		InOrder inOrder = inOrder(mowerMock);
		inOrder.verify(mowerMock).turnRight();
		inOrder.verify(mowerMock).proceedForward();
		inOrder.verify(mowerMock).turnLeft();
	}

	@Test
	public void doWork_Ok_DeployAndRunMowers() {
		// Arrange

		// Act

		// Assert

	}

}
