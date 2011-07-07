package com.mowitnow.mower;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class ProgramTest {

	@Test
	public void getMaxX_IntFound_ReturnX() throws IOException {
		// Arrange
		BufferedReader bufferedReaderStub = mock(BufferedReader.class);
		when(bufferedReaderStub.readLine()).thenReturn("5 5");
		Program program = new Program(bufferedReaderStub);

		// Act
		int x = program.getMaxX();

		// Assert
		assertThat(x).isEqualTo(5);
	}

	@Test
	public void getMaxY_IntFound_ReturnY() throws IOException {
		// Arrange
		BufferedReader bufferedReaderStub = mock(BufferedReader.class);
		when(bufferedReaderStub.readLine()).thenReturn("5 5");
		Program program = new Program(bufferedReaderStub);

		// Act
		int y = program.getMaxY();

		// Assert
		assertThat(y).isEqualTo(5);
	}

	@Test
	public void getInitialMowersPositionAndOrientation_FindWellFormated_ReturnInfoList()
			throws Exception {
		// Arrange
		BufferedReader bufferedReaderStub = mock(BufferedReader.class);
		when(bufferedReaderStub.readLine()).thenReturn("5 5")
				.thenReturn("1 2 N").thenReturn("GAGAGAGAA").thenReturn("3 3 E").thenReturn(null);
		Program program = new Program(bufferedReaderStub);

		// Act
		List<PositionAndOrientation> result = program
				.getInitialMowersPositionAndOrientation();

		// Assert
		assertThat(result).onProperty("x").containsExactly(1, 3);
		assertThat(result).onProperty("y").containsExactly(2, 3);
		assertThat(result).onProperty("orientation").containsExactly(
				Orientation.NORTH, Orientation.EAST);
	}

}
