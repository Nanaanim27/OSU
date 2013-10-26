package edu.osu.cse.misc.projections.shape;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.projections.Projections;

public class Shape3D {

	private Point3D camera;
	
	private LinkedList<Line3D> lines = new LinkedList<>();
	
	public Shape3D(Point3D camera, Line3D...lines) {
		this.lines.addAll(Arrays.asList(lines));
		this.camera = camera;
	}

	public void addLine(Line3D line) {
		this.lines.add(line);
	}
	
	public void removeLine(Line3D line) {
		this.lines.remove(line);
	}
	
	public Line3D[] getLines() {
		return this.lines.toArray(new Line3D[this.lines.size()]);
	}
	
	public Shape2D convertTo2D() {
		Point p1, p2;
		Shape2D shape = new Shape2D();
		for (Line3D line : this.lines) {
			p1 = Projections.project(line.getStart(), this.camera);
			p2 = Projections.project(line.getEnd(), this.camera);
			shape.addLine(new Line2D(p1, p2));
		}
		return shape;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Line3D line : this.lines) {
			s += line + "\n";
		}
		return s;
	}
	
	
}
