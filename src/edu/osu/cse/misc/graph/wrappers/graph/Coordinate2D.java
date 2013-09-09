package edu.osu.cse.misc.graph.wrappers.graph;

public class Coordinate2D {

    private double x, y;
    
    public Coordinate2D(double x, double y) {
	this.x = x;
	this.y = y;
    }
    
    public double getX() {
	return this.x;
    }
    
    public double getY() {
	return this.y;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
}
