package com.mowitnow.mower;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

	/**
	 * For tests only
	 * 
	 * @param bufferedReaderStub
	 * @throws IOException
	 */
	protected Program(BufferedReader bufferedReaderStub) throws IOException {
		if (bufferedReaderStub == null) {
			throw new IllegalArgumentException(
					"bufferedReaderStub must be not null");
		}
		this.bufferedReader = bufferedReaderStub;
		readSizeAndPositions();
	}

	private void readSizeAndPositions() throws IOException {
		String sizeLine = bufferedReader.readLine();
		LOGGER.debug("Line read: {}", sizeLine);
		String[] xy = sizeLine.split(" ");
		maxX = Integer.parseInt(xy[0]);
		maxY = Integer.parseInt(xy[1]);
	}

	public List<PositionAndOrientation> getInitialMowersPositionAndOrientation()
			throws Exception {
		List<PositionAndOrientation> pos = new ArrayList<PositionAndOrientation>();
		String poLine = bufferedReader.readLine();
		while (poLine != null) {
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
				throw new Exception();
			}
			PositionAndOrientation po = new PositionAndOrientation(
					Integer.parseInt(xyo[0]), Integer.parseInt(xyo[1]),
					orientation);
			pos.add(po);
			bufferedReader.readLine();
			poLine = bufferedReader.readLine();
		}
		return pos;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

}
