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
	public void runMower_InputRFL_MoveMower() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);
		Mower mowerMock = mock(Mower.class);
		when(mowerMock.getOrientation()).thenReturn(NORTH);
		when(mowerMock.getX()).thenReturn(0);
		when(mowerMock.getY()).thenReturn(0);
		controller.setMaxXForTesting(1);
		controller.setMaxYForTesting(1);
		controller.setGridForTesting(new boolean[][] { { false, false },
				{ false, false } });

		controller.runMower(mowerMock, instructions);

		InOrder inOrder = inOrder(mowerMock);
		inOrder.verify(mowerMock).turnRight();
		inOrder.verify(mowerMock).proceedForward();
		inOrder.verify(mowerMock).turnLeft();
	}

	@Test
	public void runMower_WillExceedMaxX_DontMoveMower() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.RIGHT);
		instructions.add(Instruction.FORWARD);
		instructions.add(Instruction.LEFT);
		Mower mowerMock = mock(Mower.class);
		when(mowerMock.getOrientation()).thenReturn(NORTH);
		when(mowerMock.getX()).thenReturn(0);
		when(mowerMock.getY()).thenReturn(1);
		controller.setMaxXForTesting(1);
		controller.setMaxYForTesting(1);
		controller.setGridForTesting(new boolean[][] { { false, false },
				{ false, false} });

		controller.runMower(mowerMock, instructions);

		InOrder inOrder = inOrder(mowerMock);
		inOrder.verify(mowerMock).turnRight();
		verify(mowerMock, never()).proceedForward();
		inOrder.verify(mowerMock).turnLeft();
	}

	@Test
	public void runMower() {

	}

}
