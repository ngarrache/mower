package com.mowitnow.mower;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nizar Garrache
 * 
 * @since 7 juil. 2011
 */
public class Program {
	private int maxX;
	private int maxY;
	private Map<Position, List<Instruction>> commands;

	public Program() {
		commands = new LinkedHashMap<Position, List<Instruction>>();
	}

	public void addCommand(Position po,
			List<Instruction> instructions) {
		commands.put(po, instructions);
	}

	public Set<Position> getPositionAndOrientations() {
		return commands.keySet();
	}

	public List<Instruction> getInstructionsByMower(Position po) {
		return commands.get(po);
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

}
