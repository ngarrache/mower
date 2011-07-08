package com.mowitnow.mower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nizar Garrache
 * 
 * @since 5 juil. 2011
 */
public class Controller {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Controller.class);

	private List<Mower> mowers = new ArrayList<Mower>();

	public void execute(Program program) {
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
		LOGGER.info("Mower complete job");
		LOGGER.info(mower.toString());
	}

	protected List<Mower> getMowers() {
		return mowers;
	}

}
