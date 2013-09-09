package edu.osu.cse.misc.graph.wrappers;

public class Point {

	public double x, y, z;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * Compares this Point to another.
	 * @param obj Another Object to compare this Point to.
	 * @return <tt>true</tt> if the provided Object is a Point, and x, y, and z are equal for each Point. <tt>false</tt> otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}
	
}
