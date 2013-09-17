package edu.osu.cse.misc.game.snake.wrappers.block;

public class Block {

	public final int x, y;
	public BlockType type;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = BlockType.EMPTY;
	}
	
}
