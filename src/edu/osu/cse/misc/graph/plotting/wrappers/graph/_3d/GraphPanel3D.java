package edu.osu.cse.misc.graph.plotting.wrappers.graph._3d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph.AbstractGraphPanel;
import edu.osu.cse.misc.projections.camera.Camera;

public class GraphPanel3D extends AbstractGraphPanel {

	private Camera camera = new Camera(-300f, 200f, 800f);
	
	private LinkedList<AbstractFunction3D> functions = new LinkedList<>();
	
	private float xMin, xMax;
	private float yMin, yMax;
	private float zMin, zMax;
	
	private float xInterval = 1f, yInterval = 1f, zInterval = 1f;
	
	/**
	 * Constructs a 3D coordinate system with the given axis values.
	 * 
	 * @param xMin Min x value
	 * @param xMax Max x value
	 * @param yMin Min y value
	 * @param yMax Max y value
	 * @param zMin Min z value
	 * @param zMax Max z value
	 */
	public GraphPanel3D(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
	}
	
	/**
	 * Sets the x interval to the given value.
	 * 
	 * @param xInterval A floating point value.
	 */
	public void setXInterval(float xInterval) {
		this.xInterval = xInterval;
	}
	
	/**
	 * Sets the y interval to the given value.
	 * 
	 * @param yInterval A floating point value.
	 */
	public void setYInterval(float yInterval) {
		this.yInterval = yInterval;
	}
	
	/**
	 * Sets the z interval to the given value.
	 * 
	 * @param zInterval A floating point value.
	 */
	public void setZInterval(float zInterval) {
		this.zInterval = zInterval;
	}
	
	/**
	 * @see {@link #convertToScreen(float, float, float)}
	 */
	public Point convertToScreen(Coordinate3D coord) {
		return this.convertToScreen(coord.getX(), coord.getY(), coord.getZ());
	}
	
	/**
	 * Converts a point in 3D space to a two dimensional point, such that
	 * it can be plotted on a 2D plane.
	 * 
	 * @param x The x-value of the coordinate.
	 * @param y The y-value of the coordinate.
	 * @param z The z-value of the coordinate.
	 * @return A Point relative to the panel holding this Graph marking the coordinate.
	 */
	public Point convertToScreen(float x, float y, float z) {
		y = -y;
		Point3D p = new Point3D(x, y, z);
		return this.camera.project(p);
	}
	
	/**
	 * Checks whether the given Coordinate is within the visible bounds of this Graph.
	 * 
	 * @param coord A Coordinate3D object.
	 * @return <tt>true</tt> or <tt>false</tt> whether the point is within the Graph's bounds or not.
	 */
	public boolean contains(Coordinate3D coord) {
		return (coord.getX() >= this.xMin && coord.getX() <= this.xMax) 
				&& (coord.getY() >= this.yMin && coord.getY() <= this.yMax)
				&& (coord.getZ() >= this.zMin && coord.getZ() <= this.zMax);
	}
	
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		this.drawBox(g);
	}
	
	/** Draws the "box" that holds the axes of the 3d plane. */
	private void drawBox(Graphics2D g) {
		Point3D xAxisMin = new Point3D(this.xMin, 0f, 0f), xAxisMax = new Point3D(this.xMax, 0f, 0f);
		Point3D yAxisMin = new Point3D(0f, this.yMin, 0f), yAxisMax = new Point3D(0f, this.yMax, 0f);
		Point3D zAxisMin = new Point3D(0f, 0f, this.zMin), zAxisMax = new Point3D(0f, 0f, this.zMax);
		
		Point _xAxisMin = this.camera.project(xAxisMin), _xAxisMax = this.camera.project(xAxisMax);
		Point _yAxisMin = this.camera.project(yAxisMin), _yAxisMax = this.camera.project(yAxisMax);
		Point _zAxisMin = this.camera.project(zAxisMin), _zAxisMax = this.camera.project(zAxisMax);
		
		g.drawLine(_xAxisMin.x, _xAxisMin.y, _xAxisMax.x, _xAxisMax.y);
		g.drawLine(_yAxisMin.x, _yAxisMin.y, _yAxisMax.x, _yAxisMax.y);
		g.drawLine(_zAxisMin.x, _zAxisMin.y, _zAxisMax.x, _zAxisMax.y);
	}
	
	/**
	 * The camera viewing this 3D coordinate system.
	 * 
	 * @return A Camera object that views this 3D coordinate system.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Adds a function to this Graph to be plotted.
	 * 
	 * @param function A 2D function to add.
	 */
	public void addFunction(AbstractFunction3D function) {
		this.functions.add(function);
		this.repaint();
	}

	/**
	 * Removes a function from this graph.
	 * 
	 * @param function A 3D function to be removed.
	 */
	public void removeFunction(AbstractFunction3D function) {
		this.functions.remove(function);
		this.repaint();
	}
	
	public void removeFunction(int index) {
		this.functions.remove(index);
		this.repaint();
	}
}
