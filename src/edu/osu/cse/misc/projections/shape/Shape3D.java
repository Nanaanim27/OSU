package edu.osu.cse.misc.projections.shape;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

import edu.osu.cse.misc.projections.CoordinateSystem3D;
import edu.osu.cse.misc.projections.Projections;

public class Shape3D {

	private CoordinateSystem3D camera, view;
	
	private LinkedList<Line3D> lines = new LinkedList<>();
	private Line2D[] cache;
	
	private int hash = -1;
	
	public Shape3D(CoordinateSystem3D camera, CoordinateSystem3D view, Line3D...lines) {
		this.lines.addAll(Arrays.asList(lines));
		this.camera = camera;
		this.view = view;
	}
	
	public Line2D[] convertTo2D() {
		if (this.cache == null || this.hashCode() != hash) {
			this.cache = new Line2D[this.lines.size()];
			for (int i = 0; i < this.cache.length; i++) {
				Line3D line = this.lines.get(i);
				Point p1 = Projections.project(line.getStart(), this.camera, this.view);
				Point p2 = Projections.project(line.getEnd(), this.camera, this.view);
				this.cache[i] = new Line2D(p1, p2);
			}
		}
		return cache;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (Line3D line : this.lines) {
			hash += line.hashCode();
		}
		return (this.hash = hash);
	}
	
	
}
