package edu.osu.cse.misc.graph.plotting.wrappers.graph._3d;

/**
 * Represents a point in a three dimensional coordinate system
 */
public class Coordinate3D {

	private float x, y, z;
	
	public Coordinate3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
}
