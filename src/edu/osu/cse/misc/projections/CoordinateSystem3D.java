package edu.osu.cse.misc.projections;

import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.projections.shape.Shape3D;

/**
 * A generic coordinate system representing a three-dimensional space. 
 */
public class CoordinateSystem3D {

	private LinkedList<Shape3D> shapes = new LinkedList<>();
	
	private Point3D origin;
	
	private double rotation = 0D;
	
	public CoordinateSystem3D(Point3D origin) {
		this.origin = origin;
	}
	
	public Point3D getOrigin() {
		return this.origin;
	}
	
	public Point3D[] getShapes() {
		return this.shapes.toArray(new Point3D[this.shapes.size()]);
	}
	
	public boolean addShape(Shape3D shape) {
		return this.shapes.add(shape);
	}
	
	public void setRotationDegrees(double rads) {
		this.rotation = rads;
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	/**
	 * Determines where a point from another CoordinateSystem3D would lie if it were to be positioned on
	 * <tt>this</tt> CoordinateSystem3D.
	 * <br /><br />
	 * For example, if the eye is located at P(0, 0, 0) and views an object plane at P(5, 3, 2),
	 * <br />if a point on the object plane is located at (2, 1, 4), then this point would translate to
	 * <br />the eye's coordinate space as (7, 4, 6)
	 * 
	 * @param otherPoint A Point3D from another CoordinateSystem3D
	 * @param otherPlane Another CoordinateSystem3D
	 * @return A new Point3D that represents where the provided point would lie if it were on <tt>this</tt> coordintate system.
	 */
	public Point3D getPoint(Point3D otherPoint, CoordinateSystem3D otherPlane) {
		double x = otherPlane.getOrigin().x - this.getOrigin().x + otherPoint.x;
		double y = otherPlane.getOrigin().y - this.getOrigin().y + otherPoint.y;
		double z = otherPlane.getOrigin().z - this.getOrigin().z + otherPoint.z;
		return new Point3D(x, y, z);
	}
	
}
