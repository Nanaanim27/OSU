package edu.osu.cse.misc.projections.shape;

import java.awt.Point;

/**
 * Two points in 2D space that are meant to be connected
 */
public class Line2D {

	private Point p1, p2;
	
	public Line2D(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getStart() {
		return this.p1;
	}
	
	public Point getEnd() {
		return this.p2;
	}
	
}
