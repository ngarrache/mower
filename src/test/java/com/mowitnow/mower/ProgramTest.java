package com.mowitnow.mower;

import static com.mowitnow.mower.Instruction.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class ProgramTest {
	private Program program;
	
	@Before
	public void setUp() throws Exception {
		BufferedReader bufferedReaderStub = mock(BufferedReader.class);
		when(bufferedReaderStub.readLine()).thenReturn("5 5")
				.thenReturn("1 2 N").thenReturn("GAGAGAGAA")
				.thenReturn("3 3 E").thenReturn("AADAADADDA").thenReturn(null);
		program = new Program(bufferedReaderStub);
	}

	@Test
	public void getMaxX_IntFound_ReturnX() {
		// Arrange

		// Act
		int x = program.getMaxX();

		// Assert
		assertThat(x).isEqualTo(5);
	}

	@Test
	public void getMaxY_IntFound_ReturnY() {
		// Arrange

		// Act
		int y = program.getMaxY();

		// Assert
		assertThat(y).isEqualTo(5);
	}

	@Test
	public void getInitialMowersPositionAndOrientation_FindWellFormated_ReturnInfoList() {
		// Arrange

		// Act
		List<PositionAndOrientation> result = program
				.getInitialMowersPositionAndOrientation();

		// Assert
		assertThat(result).onProperty("x").containsExactly(1, 3);
		assertThat(result).onProperty("y").containsExactly(2, 3);
		assertThat(result).onProperty("orientation").containsExactly(
				Orientation.NORTH, Orientation.EAST);
	}

	@Test
	public void getNextInstructions_FindWellFormated_ReturnInstructions() {
		// Arrange

		// Act
		List<Instruction> instructions = program.getNextInstructions();

		// Assert
		assertThat(instructions).containsExactly(LEFT, FORWARD, LEFT, FORWARD,
				LEFT, FORWARD, LEFT, FORWARD, FORWARD); // GAGAGAGAA
	}
}
