package edu.osu.cse.misc.projections.shape;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

import edu.osu.cse.misc.projections.camera.Camera;

public class Shape3D {

	private Camera camera;
	
	private LinkedList<Line3D> lines = new LinkedList<>();
	
	public Shape3D(Camera camera, Line3D...lines) {
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
			p1 = this.camera.project(line.getStart());
			p2 = this.camera.project(line.getEnd());
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
