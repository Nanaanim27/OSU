package edu.osu.cse.misc.graph.wrappers.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;

public final class Graph2D extends JPanel {

	private static final Color[] COLORS = {
		Color.red, Color.blue, Color.green, Color.cyan, Color.magenta,
		Color.pink, Color.pink, Color.orange
	};

	private int xMin, xMax;
	private int yMin, yMax;

	private Dimension size;

	private LinkedList<AbstractFunction2D> functions = new LinkedList<>();

	public Graph2D(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	@Override
	public Dimension getPreferredSize() {
		if (this.size == null) {
			this.size = new Dimension((int) (this.getXLength() * this.getXInterval()), (int) (this.getYLength() * this.getYInterval()));
		}
		return this.size;
	}

	/**
	 * The "center" point of the graph relative to the panel holding it.
	 * The center is marked by the origin (0, 0)
	 * 
	 * @return A Point marking the origin of the graph.
	 */
	public Point getCenterPoint() {
		return new Point((int) (Math.abs(this.xMin) * this.getXInterval()), (int) (Math.abs(this.yMin) * this.getYInterval())); 
	}

	/**
	 * @see {{@link #convertToScreen(double, double)}
	 */
	public Point convertToScreen(Coordinate2D coord) {
		return convertToScreen(coord.getX(), coord.getY());
	}

	/**
	 * Converts the given x/y coordinates to the Point relative to the panel holding this Graph.
	 * <br />For example, convertToScreen(0, 0) would return the point of the origin, possibly
	 * the center point, depending on whether the graph is shifted or not.
	 * 
	 * @param x The x-value of the coordinate.
	 * @param y The y-value of the coordinate
	 * @return A Point relative to the panel holding this Graph marking the coordinate.
	 */
	public Point convertToScreen(double x, double y) {
		y = -y; //coord system y-direction is flipped from swing's
		Point center = this.getCenterPoint();
		return new Point((int) (center.x + (x * this.getXInterval())), (int) (center.y + (y * this.getYInterval())));
	}

	/** The sum of the absolute values of xMin and xMax */
	public double getXLength() {
		return Math.abs(this.xMin) + Math.abs(this.xMax);
	}

	/** The sum of the absolute values of yMin and yMax */
	public double getYLength() {
		return Math.abs(this.yMin) + Math.abs(this.yMax);
	}

	/**
	 * The ratio of the pixel width of the Graph and the x-axis length.
	 * 
	 * @return (width/length) respectively based on the aforementioned description.
	 */
	public double getXInterval() {
		return 20D;
	}

	/**
	 * The ratio of the pixel height of the Graph and the y-axis length.
	 * 
	 * @return (height/length) respectively based on the aforementioned description.
	 */
	public double getYInterval() {
		return 20D;
	}

	/**
	 * Checks whether the given Coordinate is within the visible bounds of this Graph.
	 * 
	 * @param coord A Coordinate2D object.
	 * @return <tt>true</tt> or <tt>false</tt> whether the point is within the Graph's bounds or not.
	 */
	public boolean contains(Coordinate2D coord) {
		return (coord.getX() >= this.xMin && coord.getX() <= this.xMax) && (coord.getY() >= this.yMin && coord.getY() <= this.yMax);
	}

	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;

		drawGrid(g);
		drawFunctions(g);
	}

	private void drawGrid(Graphics2D g) {
		BasicStroke normal = new BasicStroke(1);
		BasicStroke axis = new BasicStroke(3);
		
		int xDraw = 0;
		for (int xValue = this.xMin; xValue <= xMax; xValue++) {
			if (xValue == 0) {
				g.setStroke(axis);
			}
			else {
				g.setStroke(normal);
			}
			g.drawLine(xDraw, 0, xDraw, this.getPreferredSize().height);
			xDraw += this.getXInterval();
		}

		int yDraw = 0;
		for (int yValue = this.yMin; yValue <= yMax; yValue++) {
			if (yValue == 0) {
				g.setStroke(axis);
			}
			else {
				g.setStroke(normal);
			}
			g.drawLine(0, yDraw, this.getPreferredSize().width, yDraw);
			yDraw += this.getYInterval();
		}

	}

	private void drawFunctions(Graphics2D g) {
		g.setStroke(new BasicStroke(1.5f));
		for (int i = 0; i < this.functions.size(); i++) {
			g.setColor(COLORS[i % COLORS.length]);
			AbstractFunction2D function = this.functions.get(i);
			if (function.isValid()) {
				Coordinate2D[] points = function.evaluateFrom(this.xMin, this.xMax, 0.05D);
				for (int p = 0; p < points.length; p++) {
					if (p < points.length - 1) {
						Point cur = this.convertToScreen(points[p]);
						Point next = this.convertToScreen(points[p+1]);
						if (this.contains(cur))
							g.drawLine((int) cur.getX(), (int) cur.getY(), (int) next.getX(), (int) next.getY());
					}
				}
			}
			else {
				this.functions.remove(function);
			}
		}
	}

	/**
	 * Adds a function to this Graph to be plotted.
	 * 
	 * @param function A 2D function to add.
	 */
	public void addFunction(AbstractFunction2D function) {
		this.functions.add(function);
	}

	/**
	 * Removes a function from this graph.
	 * 
	 * @param function A 2D function to be removed.
	 */
	public void removeFunction(AbstractFunction2D function) {
		this.functions.remove(function);
	}

}
