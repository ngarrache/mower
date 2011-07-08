package com.mowitnow.mower;

import static com.mowitnow.mower.Instruction.*;
import static com.mowitnow.mower.Orientation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mower.exception.InvalidFileFormatException;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class ProgramGenerator {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProgramGenerator.class);

	private BufferedReader reader;

	public ProgramGenerator(String programFileName) throws IOException {
		if (programFileName == null) {
			throw new IllegalArgumentException(
					"programFileName must be not null");
		}
		reader = new BufferedReader(new FileReader(programFileName));
	}

	/**
	 * @return the generated program
	 * @throws IOException
	 * @throws InvalidFileFormatException
	 */
	public Program generate() throws IOException, InvalidFileFormatException {
		Program program = new Program();
		setMaxXAndMaxY(program, reader.readLine());
		String line = reader.readLine();
		while (line != null) {
			addCommand(program, line, reader.readLine());
			line = reader.readLine();
		}
		return program;
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param program
	 * @param size
	 *            e.g. "X Y"
	 * @throws InvalidFileFormatException
	 */
	protected void setMaxXAndMaxY(Program program, String size)
			throws InvalidFileFormatException {
		LOGGER.debug("Line read: {}", size);
		if (size == null) {
			throw new InvalidFileFormatException("Invalid lawn size string ["
					+ size + "]");
		}
		String[] xy = size.split(" ");
		if (xy.length != 2) {
			throw new InvalidFileFormatException("Invalid lawn size string ["
					+ size + "]");
		}
		try {
			program.setMaxX(Integer.parseInt(xy[0]));
			program.setMaxY(Integer.parseInt(xy[1]));
		} catch (NumberFormatException e) {
			throw new InvalidFileFormatException("Invalid lawn size string ["
					+ size + "]");
		}
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param orientation
	 *            should be "N", "E", "S" or "W"
	 * @return the corresponding enum
	 * @throws InvalidFileFormatException
	 */
	protected Orientation getOrientation(String orientation)
			throws InvalidFileFormatException {
		if (orientation.equals("N")) {
			return NORTH;
		} else if (orientation.equals("E")) {
			return EAST;
		} else if (orientation.equals("S")) {
			return SOUTH;
		} else if (orientation.equals("W")) {
			return WEST;
		} else {
			throw new InvalidFileFormatException("Unknown orientation ["
					+ orientation + "]");
		}
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param instruction
	 *            should be 'D', 'G' or 'A'
	 * @return the corresponding enum
	 * @throws InvalidFileFormatException
	 */
	protected Instruction getInstruction(char instruction)
			throws InvalidFileFormatException {
		switch (instruction) {
		case 'D':
			return RIGHT;
		case 'G':
			return LEFT;
		case 'A':
			return FORWARD;
		default:
			throw new InvalidFileFormatException("Unknown instruction ["
					+ instruction + "]");
		}
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param program
	 * @param positionAndOrientationLine
	 *            e.g. "1 2 N"
	 * @param instructionsLine
	 *            e.g. "GAGAGAGAA"
	 * @throws InvalidFileFormatException
	 */
	protected void addCommand(Program program,
			String positionAndOrientationLine, String instructionsLine)
			throws InvalidFileFormatException {
		LOGGER.debug("Line read: {}", positionAndOrientationLine);

		String[] xyo = positionAndOrientationLine.split(" ");
		if (xyo.length != 3) {
			throw new InvalidFileFormatException(
					"Invalid position string ["
							+ positionAndOrientationLine + "]");
		}
		Orientation orientation = getOrientation(xyo[2]);
		Position po;
		try {
			po = new Position(Integer.parseInt(xyo[0]),
					Integer.parseInt(xyo[1]), orientation);
		} catch (NumberFormatException e) {
			throw new InvalidFileFormatException(
					"Invalid position string ["
							+ positionAndOrientationLine + "]", e);
		}

		if (instructionsLine == null) {
			throw new InvalidFileFormatException(
					"Invalid instructions string [" + instructionsLine + "]");
		}

		LOGGER.debug("Line read: {}", instructionsLine);

		List<Instruction> instructions = new ArrayList<Instruction>();
		char[] instructionArray = instructionsLine.toCharArray();
		for (char i : instructionArray) {
			instructions.add(getInstruction(i));
		}

		program.addCommand(po, instructions);
	}

	/**
	 * For tests only
	 */
	protected ProgramGenerator() {

	}

	/**
	 * For tests only
	 * 
	 * @param readerStub
	 * @throws IOException
	 */
	protected void setReaderForTesting(BufferedReader readerStub)
			throws IOException {
		if (readerStub == null) {
			throw new IllegalArgumentException("readerStub must be not null");
		}
		this.reader = readerStub;
	}

}
