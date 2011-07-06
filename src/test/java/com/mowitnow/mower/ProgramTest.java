package com.mowitnow.mower;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

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

	
}
