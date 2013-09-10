package edu.osu.cse.misc.graph.methods;


public class Maths {

	/**
	 * Converts a given value degrees to its respective amount in radians.
	 * 
	 * @param degrees A number of degrees.
	 * @return The same value of degrees in radians.
	 */
	public static double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180D;
	}

	/**
	 * Converts a given value radians to its respective amount in degrees.
	 * 
	 * @param radians A number of radians.
	 * @return The same value of radians in degrees.
	 */
	public static double radiansToDegrees(double radians) {
		return radians / Math.PI * 180D;
	}
	
}
