package edu.osu.cse.misc.graph.wrappers;

import edu.osu.cse.misc.graph.methods.Vectors;

/**
 * Represents a magnitude and direction in Three-Dimensional space.
 */
public class Vector {

	public static final Vector ZERO_VECTOR = new Vector(0D, 0D, 0D);
	
	public double x, y, z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(double x, double y) {
		this(x, y, 0);
	}
	
	/**
	 * Calculates the magnitude of this vector by taking the sqrt of the sum of each term squared.
	 * 
	 * @return The resulting magnitude of this Vector.
	 */
	public double getMagnitude() {
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}
	
	/**
	 * Determines whether another vector is orthogonal (perpendicular) to this Vector.
	 * <br />Two vectors are orthogonal if and only if their dot product is equal to 0.
	 * 
	 * @param other A Vector.
	 * @return <tt>true</tt> or <tt>false</tt> whether the two vectors are orthogonal or not.
	 */
	public boolean isOrthogonalTo(Vector other) {
		return Vectors.dot(this, other) == 0;
	}
	
	/**
	 * Determines whether another vector is parallel to this Vector.
	 * <br />Two vectors are parallel if they are scalar multiples of the same unit vector.
	 * <br />Mathematically speaking, this occurs as well when the cross product of the two vectors is 0.
	 * 
	 * @param other A Vector.
	 * @return <tt>true</tt> or <tt>false</tt> whether the two vectors are parallel or not.
	 */
	public boolean isParallelTo(Vector other) {
		return Vectors.cross(this, other).equals(Vector.ZERO_VECTOR);
	}
	
	/**
	 * Converts this Vector into a Vector with the same direction, but a magnitude of 1.
	 * 
	 * @return A new Vector representing the same direction and magnitude of 1.
	 */
	public Vector toUnitVector() {
		return Vectors.divide(this, this.getMagnitude());
	}
	
	@Override
	public String toString() {
		return "<" + x + ", " + y + ", " + z + ">";
	}

	/**
	 * Auto-Generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compares this Vector to another.
	 * @param obj Another Object to compare this Vector to.
	 * @return <tt>true</tt> if the provided Object is a Vector, and x, y, and z are equal for each vector. <tt>false</tt> otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector)) {
			return false;
		}
		Vector other = (Vector) obj;
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}
	
	
	
}
