package edu.osu.cse.misc.graph.equations.wrappers;


import edu.osu.cse.misc.graph.equations.methods.Vectors;
import edu.osu.cse.misc.graph.equations.wrappers.vector.Vector;

/**
 * The standard equation for a plane is:
 * ax+by+cz+d=0
 * 
 * An orthogonal direction vector of this plane is <a, b, c>
 * A point that lies on the plane is (x, y, z)
 * 
 */
public class Plane {

	private boolean valid;
	private Point3D p; 
	private Vector normalDirectionVector;
	private double xIntercept, yIntercept, zIntercept;
	
	/**
	 * @param p A Point that lies on the Plane.
	 * @param n A Vector that is normal (perpendicular) to the provided Point.]
	 * <br />Inherently, this vector will be normal to all points on this Plane.
	 */
	public Plane(Point3D p, Vector n) {
		this.p = p;
		this.normalDirectionVector = n;
	}
	
	/**
	 * Creates a Plane from 3 points.
	 * 
	 * @param p1 A Point in 3D space
	 * @param p2 A Point in 3D space
	 * @param p3 A Point in 3D space
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
			System.err.println("Invalid set of points. Two or more are the same.");
			valid = false;
			return;
		}
		Vector v1 = new Vector(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
		Vector v2 = new Vector(p3.x - p1.x, p3.y - p1.z, p3.z - p1.z);
		Vector orthogonalDirection = Vectors.cross(v1, v2).toUnitVector();
		
		this.p = p1;
		this.normalDirectionVector = orthogonalDirection;
	}
	
	/**
	 * Simply whether this plane was constructed from valid values or not.
	 * <br />For example, constructing a plane from three points, when two of them are the same, would result in an invalid plane.
	 * 
	 * @return <tt>true</tt> if this Plane was constructed properly. <tt>false</tt> otherwise.
	 */
	public boolean isValid() {
		return this.valid;
	}

	/**
	 * Determines whether this Plane is parallel to the provided Plane.
	 * 
	 * @param other A Plane
	 * @return <tt>true</tt> if this Plane is parallel to the provided Plane. <tt>false</tt> otherwise.
	 */
	public boolean isParallelTo(Plane other) {
		return this.getOrthogonalDirection().isParallelTo(other.getOrthogonalDirection());
	}
	
	/**
	 * Determines whether this Plane is orthogonal to the provided Plane.
	 * 
	 * @param other A Plane
	 * @return <tt>true</tt> if this Plane is orthogonal to the provided Plane. <tt>false</tt> otherwise.
	 */
	public boolean isOrthogonalTo(Plane other) {
		return this.getOrthogonalDirection().isOrthogonalTo(other.getOrthogonalDirection());
	}
	
	/**
	 * Gets the Vector that is orthogonal to this Plane.
	 * 
	 * @return A Unit Vector representing the direction that is orthogonal to this Plane.
	 */
	public Vector getOrthogonalDirection() {
		return this.normalDirectionVector.toUnitVector();
	}

}
