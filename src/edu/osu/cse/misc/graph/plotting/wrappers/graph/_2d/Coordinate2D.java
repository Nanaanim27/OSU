package edu.osu.cse.misc.graph.plotting.wrappers.graph._2d;

/**
 * Represents a point in a two dimensional coordinate system.
 */
public class Coordinate2D {

	private float x, y;

	public Coordinate2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The x-value of this Coordinate.
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * The y-value of this Coordinate 
	 */
	public float getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

}
