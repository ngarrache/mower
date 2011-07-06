package com.mowitnow.mower;

import java.io.BufferedReader;
import java.io.IOException;
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
	}

	public int getMaxX() {
		return maxX;
	}

}
