package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs;

import java.awt.BasicStroke;
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

	public final PlotInfo info = new PlotInfo();
	private Deque<AbstractFunction2D> functions = new LinkedList<>();

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
	public Plot2D(float xMin, float xMax, float yMin, float yMax) {
		this.info.xMin = xMin;
		this.info.xMax = xMax;
		this.info.yMin = yMin;
		this.info.yMax = yMax;
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
		drawGrid(g);
	}

	public boolean add(AbstractFunction2D function) {
		return this.functions.add(function);
	}

	public boolean remove(AbstractFunction2D function) {
		return this.functions.remove(function);
	}

	//Draw a standard 2D cartesian coordinate system.
	private void drawGrid(Graphics2D g) {
		Coordinate2D tempCoord;
		Point screenPoint;
		Dimension size = this.getPreferredSize();
		
		boolean cont = false;
		for (float left = 0, right = this.info.xInterval; true; left -= this.info.xInterval, right += this.info.xInterval) {
			cont = false;
			if (left >= this.info.xMin) {
				tempCoord = new Coordinate2D(left, 0);
				screenPoint = this.convertToScreen(tempCoord);
				
				if (left == 0) {
					g.setStroke(new BasicStroke(3f));
					g.drawLine(screenPoint.x, 0, screenPoint.x, size.height);
				}
				else {
					g.setStroke(new BasicStroke(1f));
					g.drawLine(screenPoint.x, 0, screenPoint.x, size.height);
				}
				
				cont = true;
			}
			if (right <= this.info.xMax) {
				tempCoord = new Coordinate2D(right, 0);
				screenPoint = this.convertToScreen(tempCoord);
				g.drawLine(screenPoint.x, 0, screenPoint.x, size.height);
				
				cont = true;
			}
			
			if (!cont)
				break;
		}
		
		for (float up = 0, down = this.info.yInterval; true; up -= this.info.yInterval, down += this.info.yInterval) {
			cont = false;
			if (up >= this.info.yMin) {
				tempCoord = new Coordinate2D(up, 0);
				screenPoint = this.convertToScreen(tempCoord);
				
				if (up == 0) {
					g.setStroke(new BasicStroke(3f));
					g.drawLine(0, screenPoint.y, size.width, screenPoint.y);
				}
				else {
					g.setStroke(new BasicStroke(1f));
					g.drawLine(0, screenPoint.y, size.width, screenPoint.y);
				}
				
				cont = true;
			}
			if (down <= this.info.yMax) {
				tempCoord = new Coordinate2D(down, 0);
				screenPoint = this.convertToScreen(tempCoord);
				g.drawLine(0, screenPoint.y, size.width, screenPoint.y);
				
				cont = true;
			}
			
			if (!cont)
				break;
		}
		
	}

	@Override
	public Dimension getPreferredSize() {
		return SIZE;
	}

	/**
	 * Converts the given Coordinate to its respective Point on the screen.
	 * 
	 * @param coord A Coordinate2D that lies on the plane.
	 * @return The Point where this coordinate lies on the screen.
	 */
	public Point convertToScreen(Coordinate2D coord) {
		System.out.print("Converting to screen: " + coord);
		float magnitude_xIn = coord.getX() - this.info.xMax;
		float magnitude_yIn = coord.getY() - this.info.yMin;
		float xRatio = magnitude_xIn / this.info.getXLength(); //A % of how far right the x-axis this point lies.
		float yRatio = magnitude_yIn / this.info.getYLength(); //A % of how far down the y-axis this point lies.
		Point screen = new Point((int) (this.getPreferredSize().width * xRatio), (int) (this.getPreferredSize().height * yRatio));
		System.out.print("\tResult: " + screen + "\n");
		return screen;
	}

	/**
	 * The Coordinate2D that is closest to where the given Point lies on the plane
	 * 
	 * @param screenPoint A Point on the screen, relative to the plane
	 * @return A Coordinate2D that is closest to where the given Point lies on the plane.
	 */
	public Coordinate2D convertToCoordinateSystem(Point screenPoint) {
		float xScale = screenPoint.x / this.getPreferredSize().width; //A value from [0-1], represents the % to the right
		float yScale = screenPoint.y / this.getPreferredSize().height; //A value from [0-1], represents the % to the bottom

		float distRight = xScale * this.info.getXLength();
		float distDown = yScale * this.info.getYLength();
		
		return new Coordinate2D(this.info.xMin + distRight, this.info.yMin + distDown);
	}

	/**
	 * An instanced class that holds plot info relative to Plot2D.this
	 */
	private class PlotInfo {
		public float xMin, xMax, yMin, yMax;
		
		/**
		 * The interval at which each gridline appears over the range of yMin - yMax.
		 * <p>Over the range of -20 - +20, an xInterval of 2 would result in 20 vertical
		 * grid lines being drawn at each even value of y.</p> 
		 */
		public float xInterval = 1f;
		
		/**
		 * The interval at which each gridline appears over the range of xMin - xMax.
		 * <p>Over the range of -20 - +20, an yInterval of 2 would result in 20 horizontal
		 * grid lines being drawn at each even value of x.</p> 
		 */
		public float yInterval = 1f;
		
		/**
		 * The length of the x-axis as a scaler value.
		 * 
		 * @return The 'length' of the x-axis.
		 */
		public float getXLength() {
			return this.xMax - this.xMin;
		}

		/**
		 * The length of the y-axis as a scaler value.
		 * 
		 * @return The 'length' of the y-axis.
		 */
		public float getYLength() {
			return this.yMax - this.yMin;
		}


		/**
		 * The screen point where the Origin O(0, 0) lies.
		 * 
		 * @return The screen point where the Origin O(0, 0) lies.
		 */
		public Point getOriginPoint() {
			return Plot2D.this.convertToScreen(new Coordinate2D(0, 0));
		}
	}
	
}
