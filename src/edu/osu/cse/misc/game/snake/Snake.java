package edu.osu.cse.misc.game.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.game.snake.wrappers.Direction;
import edu.osu.cse.misc.game.snake.wrappers.block.Block;
import edu.osu.cse.misc.game.snake.wrappers.block.BlockChain;
import edu.osu.cse.misc.game.snake.wrappers.field.GameField;

public class Snake extends JPanel implements KeyListener {

	private JFrame mainFrame;

	/** Time in ms between each game tick */
	private int tickSpeed = 65;
	public GameField field;
	public BlockChain snakeLine;
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?> currentScheduledTask;

	private Dimension renderDimension;
	private BufferedImage renderImage;
	private Graphics renderGraphics;

	public Snake(int width, int height) {
		this.field = new GameField(this, width, height);
		this.snakeLine = new BlockChain(this, this.field.getInitialHead());

		this.mainFrame = new JFrame();
		this.setScore(0);
		this.mainFrame.add(this);
		this.mainFrame.pack();
		this.mainFrame.setVisible(true);
		this.mainFrame.setResizable(false);
		this.mainFrame.setLocationRelativeTo(null);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.requestFocus();
	}
	
	public void start() {
		this.currentScheduledTask = createScheduledTask();
	}

	public void tick() {
		snakeLine.update();
		repaint();
	}

	public void setScore(int score) {
		this.mainFrame.setTitle("Snake | Channeled | Score: " + score);
	}

	public void end(int finalScore) {
		System.out.println("Game Over. Final score: " + finalScore);
		System.exit(0);
	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension((this.field.width * Block.SIZE) + 1, (this.field.height * Block.SIZE) + 1);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (renderDimension == null || renderImage == null || renderGraphics == null) {
			renderDimension = this.getSize();
			renderImage = new BufferedImage(renderDimension.width, renderDimension.height, BufferedImage.TYPE_INT_ARGB);
			renderGraphics = renderImage.getGraphics();
		}
		renderGraphics.setColor(Color.black);
		renderGraphics.fillRect(0, 0, renderDimension.width, renderDimension.height);

		this.field.draw(renderGraphics);
		g.drawImage(renderImage, 0, 0, this);
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

	public static void main(String[] args) {
		new Snake(30, 30).start();
	}
}
