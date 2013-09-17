package edu.osu.cse.misc.game.snake.wrappers;

public enum Direction {

	NORTH,
	SOUTH,
	EAST,
	WEST,
	NONE;
	
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
