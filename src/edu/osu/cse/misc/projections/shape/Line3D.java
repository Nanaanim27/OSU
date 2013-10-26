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
	/**
	 * Auto-Generated
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.p1 == null) ? 0 : this.p1.hashCode());
		result = prime * result + ((this.p2 == null) ? 0 : this.p2.hashCode());
		return result;
	}

	@Override
	/**
	 * Auto-Generated
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line3D other = (Line3D) obj;
		if (this.p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!this.p1.equals(other.p1))
			return false;
		if (this.p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!this.p2.equals(other.p2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.p1 + " --> " + this.p2;
	}
	
}
