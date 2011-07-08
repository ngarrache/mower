package com.mowitnow.mowerautomaton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mowerautomaton.model.Instruction;
import com.mowitnow.mowerautomaton.model.Mower;
import com.mowitnow.mowerautomaton.model.Position;
import com.mowitnow.mowerautomaton.model.Program;

/**
 * @author Nizar Garrache
 * 
 * @since 5 juil. 2011
 */
public class Controller {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Controller.class);

	private List<Mower> mowers = new ArrayList<Mower>();
	private int maxX;
	private int maxY;
	private boolean[][] grid;

	public void execute(Program program) {
		maxX = program.getMaxX();
		maxY = program.getMaxY();
		grid = new boolean[maxX + 1][maxY + 1];

		Iterator<Position> positionsIterator = program
				.getPositionAndOrientations().iterator();
		while (positionsIterator.hasNext()) {
			deployMower(positionsIterator.next());
		}
		for (Mower mower : mowers) {
			List<Instruction> instructions = program
					.getInstructionsByMower(mower.getPosition());
			runMower(mower, instructions);
		}
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param position
	 */
	protected void deployMower(Position position) {
		Mower mower = new Mower(position);
		mowers.add(mower);
	}

	/**
	 * Exposed only for tests
	 * 
	 * @param mower
	 * @param instructions
	 */
	protected void runMower(Mower mower, List<Instruction> instructions) {
		for (Instruction instruction : instructions) {
			switch (instruction) {
			case FORWARD:
				if (canMoveMower(mower)) {
					mower.proceedForward();
					grid[mower.getX()][mower.getY()] = true;
				}
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
		LOGGER.info("Mower complete job");
		LOGGER.info(mower.toString());
	}

	/**
	 * Check for border exceed and mowers overlap
	 * 
	 * @param mower
	 * @return
	 */
	private boolean canMoveMower(Mower mower) {
		switch (mower.getOrientation()) {
		case NORTH:
			if (mower.getY() < maxY && !grid[mower.getX()][mower.getY() + 1]) {
				return true;
			}
			break;
		case EAST:
			if (mower.getX() < maxX && !grid[mower.getX() + 1][mower.getY()]) {
				return true;
			}
			break;
		case SOUTH:
			if (mower.getY() > 0 && !grid[mower.getX()][mower.getY() - 1]) {
				return true;
			}
			break;
		case WEST:
			if (mower.getY() > 0 && !grid[mower.getX() - 1][mower.getY()]) {
				return true;
			}
			break;
		default:
			throw new AssertionError("Unknown orientation ["
					+ mower.getOrientation() + "]");
		}
		return false;
	}

	protected List<Mower> getMowers() {
		return mowers;
	}

	protected void setMaxXForTesting(int maxX) {
		this.maxX = maxX;
	}

	protected void setMaxYForTesting(int maxY) {
		this.maxY = maxY;
	}

	protected void setGridForTesting(boolean[][] grid) {
		this.grid = grid;
	}

}
