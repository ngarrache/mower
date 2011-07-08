package com.mowitnow.mowerautomaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;

import com.mowitnow.mowerautomaton.exception.InvalidFileFormatException;
import com.mowitnow.mowerautomaton.model.Program;

/**
 * @author Nizar Garrache
 * 
 * @since 8 juil. 2011
 */
public class StartupIT {
	private BufferedReader reader;
	private static final String PROGRAM_FILE_NAME = "MowerProgram.txt";

	@Before
	public void setUp() {
		InputStreamReader isr = new InputStreamReader(
				ClassLoader.getSystemResourceAsStream(PROGRAM_FILE_NAME));
		reader = new BufferedReader(isr);
	}

	@Test
	public void shouldRunAutomatonSuccessfully() throws IOException,
			InvalidFileFormatException {
		ProgramGenerator programGenerator = new ProgramGenerator();
		programGenerator.setReaderForTesting(reader);
		Program program = programGenerator.generate(); 
		Controller controller = new Controller();
		controller.execute(program);
	}

}
