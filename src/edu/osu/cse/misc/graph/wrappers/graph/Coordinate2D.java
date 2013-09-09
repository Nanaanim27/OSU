package edu.osu.cse.misc.graph.wrappers.graph;

/**
 * Represents a point in a two dimensional coordinate system.
 */
public class Coordinate2D {

    private double x, y;
    
    public Coordinate2D(double x, double y) {
	this.x = x;
	this.y = y;
    }
    
    /**
     * The x-value of this Coordinate.
     */
    public double getX() {
	return this.x;
    }
    
    /**
     * The y-value of this Coordinate 
     */
    public double getY() {
	return this.y;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
}
