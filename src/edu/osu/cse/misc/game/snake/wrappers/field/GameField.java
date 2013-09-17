package edu.osu.cse.misc.game.snake.wrappers.field;

import java.util.Random;

import edu.osu.cse.misc.game.snake.wrappers.block.Block;
import edu.osu.cse.misc.game.snake.wrappers.block.BlockType;

/**
 * A grid of blocks that houses the visual aspects of the game.
 */
public class GameField {

	private int width, height;
	private Block[][] blocks;
	
	public GameField(int width, int height) {
		this.width = width;
		this.height = height;
		this.blocks = new Block[height][width];
		
		Random rand = new Random();
		int startX = rand.nextInt(width);
		int startY = rand.nextInt(height);
		if (startX == width/2 && startY == height/2) {
			startX++;
			startY--;
		}
		
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				this.blocks[h][w] = new Block(w, h);
				if (h == startY && w == startX) {
					this.getBlock(w, h).type = BlockType.SNAKE;
				}
				else if (h == height/2 && w == width/2) {
					this.getBlock(w, h).type = BlockType.FOOD;
				}
			}
		}
	}
	
	public Block getBlock(int x, int y) {
		if (x < 0 || y < 0) {
			return null;
		}
		if (x >= this.width || y >= this.height) {
			return null;
		}
		return this.blocks[y][x];
	}
	
	
}
