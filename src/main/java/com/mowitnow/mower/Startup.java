package com.mowitnow.mower;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mower.exception.InvalidFileFormatException;

/**
 * @author Nizar Garrache
 * 
 * @since 8 juil. 2011
 */
public class Startup {
	private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				LOGGER.error("Usage: Startup <program file path>");
				System.exit(-1);
			}

			ProgramGenerator programGenerator = new ProgramGenerator(args[0]);
			Program program = programGenerator.generate();
			Controller controller = new Controller();
			controller.execute(program);

		} catch (IOException e) {
			LOGGER.error("I/O error", e);
		} catch (InvalidFileFormatException e) {
			LOGGER.error("Invalid program file format", e);
		} catch (Exception e) {
			LOGGER.error("Unknown error", e);
		}
	}
}
