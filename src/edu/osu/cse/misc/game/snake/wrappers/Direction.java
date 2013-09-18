package edu.osu.cse.misc.game.snake.wrappers;

import java.awt.event.KeyEvent;

public enum Direction {

	NORTH(KeyEvent.VK_UP),
	SOUTH(KeyEvent.VK_DOWN),
	EAST(KeyEvent.VK_RIGHT),
	WEST(KeyEvent.VK_LEFT),
	NONE(-1);
	
	private int automationKeyCode;
	
	Direction(int automationKeyCode) {
		this.automationKeyCode = automationKeyCode;
	}
	
	public int getAutomationKeyCode() {
		return this.automationKeyCode;
	}
	
	public static Direction getOppositeDirection(Direction d) {
		switch(d) {
			case NORTH:
				return Direction.SOUTH;
			case SOUTH:
				return Direction.NORTH;
			case EAST:
				return Direction.WEST;
			case WEST:
				return Direction.EAST;
			default:
				return Direction.NONE;
			
		}
	}
	
}
