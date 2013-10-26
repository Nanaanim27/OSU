package edu.osu.cse.misc.projections.shape;

import java.util.Arrays;
import java.util.LinkedList;

public class Shape2D {

	private LinkedList<Line2D> lines = new LinkedList<>();

	public Shape2D(Line2D...lines) {
		this.lines.addAll(Arrays.asList(lines));
	}
	
	public void addLine(Line2D line) {
		this.lines.add(line);
	}
	
	public void removeLine(Line2D line) {
		this.lines.remove(line);
	}
	
	public Line2D[] getLines() {
		return this.lines.toArray(new Line2D[this.lines.size()]);
	}

	@Override
	public String toString() {
		String s = "";
		for (Line2D line : this.lines) {
			s += line + "\n";
		}
		return s;
	}

}
