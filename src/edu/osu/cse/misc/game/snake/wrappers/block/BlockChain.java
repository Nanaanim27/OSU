package edu.osu.cse.misc.game.snake.wrappers.block;

import java.util.LinkedList;

import edu.osu.cse.misc.game.snake.Snake;
import edu.osu.cse.misc.game.snake.wrappers.Direction;

public class BlockChain {

	public Snake game;
	public LinkedList<Block> chain = new LinkedList<>();

	public BlockChain(Snake game, Block startBlock) {
		this.game = game;
		startBlock.direction = Direction.EAST;
		this.chain.add(startBlock);
	}

	public void update() {
		try {
			Block currentHead = this.chain.getFirst();
			Direction dir = currentHead.direction;
			Block next;
			switch(dir) {
				case NORTH:
					next = this.game.field.getBlock(currentHead.x, currentHead.y - 1);
					break;
				case SOUTH:
					next = this.game.field.getBlock(currentHead.x, currentHead.y + 1);
					break;
				case EAST:
					next = this.game.field.getBlock(currentHead.x + 1, currentHead.y);
					break;
				case WEST:
					next = this.game.field.getBlock(currentHead.x - 1, currentHead.y);
					break;
				default:
					throw new RuntimeException("Head of snake has no direction. It must have one.");
			}
			if (noViolations(next)) {
				next.direction = dir;
				this.chain.addFirst(next);
				if (next.type != BlockType.FOOD)
					this.chain.removeLast().type = BlockType.EMPTY;
				else
					this.game.field.addFood();
				next.type = BlockType.SNAKE;
			}
			else {
				this.game.end(this.chain.size() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDirection(Direction newDirection) {
		System.out.println("Updating direction to: " + newDirection);
		this.chain.getFirst().direction = newDirection;
	}
	
	public Direction getDirection() {
		return this.chain.getFirst().direction;
	}

	private boolean noViolations(Block next) {
		return next.type != BlockType.SNAKE;
	}

}
