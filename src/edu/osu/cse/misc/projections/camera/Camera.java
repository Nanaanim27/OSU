package edu.osu.cse.misc.projections.camera;

import java.awt.Point;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;

public class Camera extends Point3D {

	private double nearDistance = 400;
	
	private double angRadsX = 0D, angRadsY = 0D;
	
	public Camera() {
		this(0D, 0D, 0D);
	}
	
	public Camera(double x, double y, double z) {
		super(x, y, z);
	}
	
	public double getViewingDistance() {
		return this.nearDistance;
	}
	
	public void setViewingDistance(double viewingDistance) {
		this.nearDistance = viewingDistance;
		if (this.nearDistance < 0)
			this.nearDistance = 0;
	}
	
	public void setRotationX(double angRads) {
		
	}
	
	public Point project(Point3D p) {
		
		double x = p.x - this.x;
		double y = p.y - this.y;
		double z = p.z - this.z;
		
		double _x = x * (this.getViewingDistance() / z);
		double _y = y * (this.getViewingDistance() / z);
		return new Point((int) _x, (int) _y);
	}
	
}
