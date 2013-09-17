package edu.osu.cse.misc.game.snake.wrappers.field;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import edu.osu.cse.misc.game.snake.Snake;
import edu.osu.cse.misc.game.snake.wrappers.block.Block;
import edu.osu.cse.misc.game.snake.wrappers.block.BlockType;

/**
 * A grid of blocks that houses the visual aspects of the game.
 */
public class GameField {

	public final int width, height;
	private final Block[][] blocks;
	public final Snake game;
	private Block initialHead;
	private Random random = new Random();
	
	public GameField(Snake game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.blocks = new Block[height][width];
		
		int startFoodX = random.nextInt(width-2) + 1;
		int startFoodY = random.nextInt(height-2) + 1;
		if (startFoodX == width/2 && startFoodY == height/2) {
			startFoodX++;
			startFoodY--;
		}
		
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				this.blocks[h][w] = new Block(this, w, h);
				if (h == startFoodY && w == startFoodX) {
					this.getBlock(w, h).type = BlockType.FOOD;
				}
				else if (h == 0 || w == 0 || h == height - 1 || w == width -1) {
					this.getBlock(w, h).type = BlockType.SNAKE;
				}
				else if (h == height/2 && w == width/2) {
					this.getBlock(w, h).type = BlockType.SNAKE;
					this.initialHead = this.getBlock(w, h);
				}
			}
		}
	}
	
	public void addFood() {
		LinkedList<Block> allBlocks = getBlocks();
		allBlocks.removeAll(Arrays.asList(game.snakeLine.chain));
		allBlocks.get(random.nextInt(allBlocks.size())).type = BlockType.FOOD;
		this.game.repaint();
	}
	
	public Block getInitialHead() {
		return this.initialHead;
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
	
	/**
	 * Gathers all of the Blocks in the grid, excluding the walls, into one list
	 * 
	 * @return A list of Blocks
	 */
	public LinkedList<Block> getBlocks() {
		LinkedList<Block> blocks = new LinkedList<>();
		for (int h = 1; h < this.height-1; h++) {
			for (int w = 1; w < this.width-1; w++) {
				blocks.add(this.getBlock(w, h));
			}
		}
		return blocks;
	}
	
	public void draw(Graphics g) {
		Block next;
		for (int width = 0; width < this.blocks[0].length; width++) {
			for (int height = 0; height < this.blocks.length; height++) {
				next = this.getBlock(width, height);
				if (next != null) {
					g.setColor(next.type.getColor());
					g.fillRect(next.x * Block.SIZE, next.y * Block.SIZE, Block.SIZE, Block.SIZE);
					g.setColor(Color.black);
					g.drawRect(next.x * Block.SIZE, next.y * Block.SIZE, Block.SIZE, Block.SIZE);
				}
				else {
					System.out.println("Null Block at: " + width + ", " + height);
				}
			}
		}
	}
}
