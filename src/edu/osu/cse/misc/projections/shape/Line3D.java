package edu.osu.cse.misc.projections.shape;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;

/**
 * Two points in 3D space that are meant to be connected. 
 */
public class Line3D {

	private Point3D p1, p2;
	
	public Line3D(Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point3D getStart() {
		return this.p1;
	}
	
	public Point3D getEnd() {
		return this.p2;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line3D other = (Line3D) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Line3D l = new Line3D(new Point3D(2, 2, 2), new Point3D(4, 1, 2));
		System.out.println(l.hashCode());
	}
	
}
