package com.mowitnow.mower;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nizar Garrache
 * 
 * @since 5 juil. 2011
 */
public class MowersController {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(MowersController.class);

	private int maxXCoordinate;
	private int maxYCoordinate;

	private List<Mower> mowers = new ArrayList<Mower>();

	public void initLawn(int maxXCoordinate, int maxYCoordinate) {
		if (maxXCoordinate < 0 || maxYCoordinate < 0) {
			throw new IllegalArgumentException(
					"maxXCoordinate and maxYCoordinate should be positives");
		}
		this.maxXCoordinate = maxXCoordinate;
		this.maxYCoordinate = maxYCoordinate;
	}

	public void deployMower(int x, int y, CardinalDirection orientation) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x and y should be positives");
		}
		Mower mower = new Mower(x, y, orientation);
		mowers.add(mower);
	}

	public void runMower(Mower mower, List<Instruction> instructions) {
		for (Instruction instruction : instructions) {
			switch (instruction) {
			case FORWARD:
				mower.proceedForward();
				break;
			case RIGHT:
				mower.turnRight();
				break;
			case LEFT:
				mower.turnLeft();
				break;
			default:
				throw new AssertionError("Unknown instruction [" + instruction
						+ "]");
			}
		}
		LOGGER.info("Mower finished work: " + mower.getxCoordinate() + ","
				+ mower.getyCoordinate() + " " + mower.getDirection());
	}
	
	

	public int getMaxXCoordinate() {
		return maxXCoordinate;
	}

	public int getMaxYCoordinate() {
		return maxYCoordinate;
	}

	public List<Mower> getMowers() {
		return mowers;
	}

}
