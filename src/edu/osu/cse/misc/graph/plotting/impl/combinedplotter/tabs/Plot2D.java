package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

/**
 * A canvas that supports the drawing of Two-Dimensional functions
 */
public class Plot2D extends Canvas {


	private static final Dimension SIZE = new Dimension(500, 500);

	private Deque<AbstractFunction2D> functions = new LinkedList<>();

	private int xMin, xMax, yMin, yMax;

	/**
	 * Creates a 2D coordinate system with the given axis values.
	 * <br />The length of the x-axis would be the addition of the absolute values of xMin and xMax.
	 * <br />The length of the y-axis would be the same value respective to the y-values.
	 * 
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 */
	public Plot2D(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	@Override
	/**
	 * Painting the coordinate system assumes the origin to be at O(0, 0) as a screen point.
	 * The graphics plane will be translated accordingly so that the entire coordinate system
	 * can be displayed.
	 */
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;

	}

	public boolean add(AbstractFunction2D function) {
		return this.functions.add(function);
	}

	public boolean remove(AbstractFunction2D function) {
		return this.functions.remove(function);
	}

	private void drawGrid(Graphics2D g) {
		
	}

	@Override
	public Dimension getPreferredSize() {
		return SIZE;
	}

	public Point convertToScreen(Coordinate2D coord) {
		
	}

	/**
	 * 
	 * 
	 * @param screenPoint
	 * @return
	 */
	public Coordinate2D convertToCoordinateSystem(Point screenPoint) {
		//For the scale values, [0, 0.5) represents Quadrent 2 and 3 (left)
		//For the scale values, [0.5, 1] represents Quadrent 1 and 4 (right)
		float xScale = screenPoint.x / this.getPreferredSize().width; //A value from [0-1]
		float yScale = screenPoint.y / this.getPreferredSize().height; //A value from [0-1]

		return new Coordinate2D(coordX, coordY);
	}

	/**
	 * The length of the x-axis as a scaler value.
	 * 
	 * @return The 'length' of the x-axis.
	 */
	private int getXLength() {
		return Math.abs(this.xMin) + Math.abs(this.xMax);
	}

	/**
	 * The length of the y-axis as a scaler value.
	 * 
	 * @return The 'length' of the y-axis.
	 */
	private int getYLength() {
		return Math.abs(this.yMin) + Math.abs(this.yMax);
	}

	/**
	 * The screen point where the Origin O(0, 0) lies.
	 * 
	 * @return The screen point where the Origin O(0, 0) lies.
	 */
	public Point getCenterPoint() {
		float xOriginRelative = (this.getXLength() - this.xMax) / ((float) this.getXLength());
		float yOriginRelative = (this.getYLength() - this.yMax) / ((float) this.getYLength());
		return new Point((int) (this.getPreferredSize().width * xOriginRelative), (int) (this.getPreferredSize().height * yOriginRelative));
	}
}
