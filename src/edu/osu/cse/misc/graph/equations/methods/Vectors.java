package edu.osu.cse.misc.graph.equations.methods;

import edu.osu.cse.misc.graph.equations.wrappers.vector.Vector;

public abstract class Vectors {

	/**
	 * Sums two vectors.
	 * 
	 * @param v1 A Vector
	 * @param v2 A Vector
	 * @return A new Vector representing the resultant of the two provided Vectors after addition.
	 */
	public static Vector sum(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}
	
	/**
	 * Subtracts v2 from v1.
	 * <br />Mathematically speaking, adds the inverse of v2 to v1.
	 * 
	 * @param v1 The primary vector to subtract from.
	 * @param v2 The secondary vector.
	 * @return A new Vector representing the resultant of the two provided Vectors after subtraction.
	 */
	public static Vector subtract(Vector v1, Vector v2) {
		return sum(v1, multiply(v2, -1));
	}
	
	/**
	 * Multiplies the given Vector by a scaler constant.
	 * 
	 * @param v1 A Vector
	 * @param scale A scaler constant to multiply the provided Vector by.
	 * @return A new Vector with its magnitude scaled by the given value.
	 */
	public static Vector multiply(Vector v1, double scale) {
		return new Vector(v1.x * scale, v1.y * scale, v1.z * scale);
	}
	
	/**
	 * Divides the given Vector by a scalar constant.
	 * 
	 * @param v1 A Vector
	 * @param scale A scalar constant to divide the provided Vector by.
	 * @return A new Vector with its magnitude scaled by the given value.
	 */
	public static Vector divide(Vector v1, double scale) {
		return multiply(v1, (1/scale));
	}
	
	/**
	 * Performs the dot product on the two given Vectors.
	 * 
	 * @param v1 A Vector
	 * @param v2 A Vector
	 * @return A scalar value representing the dot of the two given Vectors.
	 */
	public static double dot(Vector v1, Vector v2) {
		return ((v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z));
	}
	
	/**
	 * Performs the cross product on the two given Vectors. The resulting Vector will be orthogonal to each of the provided Vectors.
	 * Mathematically speaking, performs v1 X v2
	 * 
	 * @param v1 A Vector
	 * @param v2 A Vector
	 * @return A new Vector representing the cross of the two given Vectors.
	 */
	public static Vector cross(Vector v1, Vector v2) {
		double iDeterminant = (v1.y * v2.z) - (v1.z * v2.y);
		double jDeterminant = (v1.x * v2.z) - (v1.z * v2.x);
		double kDeterminant = (v1.x * v2.y)- (v1.y * v2.x);
		return new Vector(iDeterminant, -(jDeterminant), kDeterminant);
	}
	
}
