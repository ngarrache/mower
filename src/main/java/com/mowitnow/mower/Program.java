package com.mowitnow.mower;

import static com.mowitnow.mower.Instruction.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nizar Garrache
 * 
 * @since 6 juil. 2011
 */
public class Program {
	private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);

	private BufferedReader bufferedReader;

	private int maxX;
	private int maxY;
	private List<List<Instruction>> instructions = new ArrayList<List<Instruction>>();
	private List<PositionAndOrientation> pos = new ArrayList<PositionAndOrientation>();

	private int mowerIndex;

	public Program(String programFileName) throws IOException {
		if (programFileName == null) {
			throw new IllegalArgumentException(
					"programFileName must be not null");
		}
		bufferedReader = new BufferedReader(new FileReader(programFileName));
		parseFile();
	}

	/**
	 * For tests only
	 * 
	 * @param bufferedReaderStub
	 * @throws IOException
	 * @throws Exception
	 */
	protected Program(BufferedReader bufferedReaderStub) throws IOException {
		if (bufferedReaderStub == null) {
			throw new IllegalArgumentException(
					"bufferedReaderStub must be not null");
		}
		this.bufferedReader = bufferedReaderStub;
		parseFile();
	}

	private void setMaxXAndMaxY(String sizeLine) {
		LOGGER.debug("Line read: {}", sizeLine);
		String[] xy = sizeLine.split(" ");
		maxX = Integer.parseInt(xy[0]);
		maxY = Integer.parseInt(xy[1]);
	}

	private void addMowerPositionAndOrientation(String poLine) {
		LOGGER.debug("Line read: {}", poLine);
		String[] xyo = poLine.split(" ");
		Orientation orientation = null;
		if (xyo[2].equals("N")) {
			orientation = Orientation.NORTH;
		} else if (xyo[2].equals("E")) {
			orientation = Orientation.EAST;
		} else if (xyo[2].equals("S")) {
			orientation = Orientation.SOUTH;
		} else if (xyo[2].equals("W")) {
			orientation = Orientation.WEST;
		} else {
			throw new AssertionError("Unknown orientation char [" + xyo[2]
					+ "]");
		}
		PositionAndOrientation po = new PositionAndOrientation(
				Integer.parseInt(xyo[0]), Integer.parseInt(xyo[1]), orientation);
		pos.add(po);
	}

	private void addMowerInstructions(String instructionsLine) {
		LOGGER.debug("Line read: {}", instructionsLine);
		char[] instruction = instructionsLine.toCharArray();
		List<Instruction> instructions = new ArrayList<Instruction>();
		for (char i : instruction) {
			if (i == 'D') {
				instructions.add(RIGHT);
			} else if (i == 'G') {
				instructions.add(LEFT);
			} else if (i == 'A') {
				instructions.add(FORWARD);
			} else {
				throw new AssertionError("Unknown instruction char [" + i + "]");
			}
		}
		this.instructions.add(instructions);
	}

	private void parseFile() throws IOException {
		setMaxXAndMaxY(bufferedReader.readLine());
		String line = bufferedReader.readLine();
		while (line != null) {
			addMowerPositionAndOrientation(line);
			addMowerInstructions(bufferedReader.readLine());
			line = bufferedReader.readLine();
		}
	}

	public List<PositionAndOrientation> getInitialMowersPositionAndOrientation() {
		return pos;
	}

	public List<Instruction> getNextInstructions() {
		return instructions.get(mowerIndex++);
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

}
