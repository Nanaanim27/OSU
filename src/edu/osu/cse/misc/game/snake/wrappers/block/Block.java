package edu.osu.cse.misc.game.snake.wrappers.block;

import edu.osu.cse.misc.game.snake.wrappers.Direction;
import edu.osu.cse.misc.game.snake.wrappers.field.GameField;

public class Block {

	public static final int SIZE = 15;
	
	public final int x, y;
	public BlockType type;
	public Direction direction;
	public Block parent;
	public boolean isPath = false;

	public Block(GameField field, int x, int y) {
		this.x = x;
		this.y = y;
		this.type = BlockType.EMPTY;
		this.direction = Direction.NONE;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

}
