package edu.osu.cse.misc.game.snake;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import edu.osu.cse.misc.game.snake.wrappers.Direction;
import edu.osu.cse.misc.game.snake.wrappers.block.Block;
import edu.osu.cse.misc.game.snake.wrappers.block.BlockChain;
import edu.osu.cse.misc.game.snake.wrappers.field.GameField;

public class Snake extends Applet implements KeyListener {

	/** Time in ms between each game tick */
	private int tickSpeed = 75;
	public GameField field;
	public BlockChain snakeLine;
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?> currentScheduledTask;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.field.draw(g);
	}

	public void tick() {
		try {
			snakeLine.update();
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		int width = 30, height = 30;
		this.field = new GameField(this, width, height);

		this.snakeLine = new BlockChain(this, this.field.getInitialHead());
		this.addKeyListener(this);
		this.setSize(new Dimension((this.field.width * Block.SIZE) + 1, (this.field.height * Block.SIZE) + 1));
		this.setVisible(true);
		this.requestFocus();
		this.currentScheduledTask = createScheduledTask();
	}

	public void end(int finalScore) {
		System.out.println("Game Over. Final score: " + finalScore);
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction nextDirection = getDirection(e);
		Direction currentDirection = Snake.this.snakeLine.getDirection();
		if (nextDirection != Direction.NONE && nextDirection != currentDirection) {
			if (this.snakeLine.chain.size() == 1 || nextDirection != Direction.getOppositeDirection(currentDirection)) {
				Snake.this.snakeLine.setDirection(getDirection(e));
				this.currentScheduledTask = createScheduledTask();
			}
		}
	}
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }

	private ScheduledFuture<?> createScheduledTask() {
		if (this.currentScheduledTask != null) {
			this.currentScheduledTask.cancel(true);
		}
		return this.executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				tick();
			}
		}, 0L, this.tickSpeed, TimeUnit.MILLISECONDS);
	}

	private Direction getDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				return Direction.NORTH;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				return Direction.SOUTH;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				return Direction.WEST;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				return Direction.EAST;

			default:
				return Direction.NONE;
		}
	}
}
