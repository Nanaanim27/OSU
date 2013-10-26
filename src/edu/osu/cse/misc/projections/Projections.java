package edu.osu.cse.misc.projections;

import java.awt.Point;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;

public class Projections {

	private static final double DISTANCE = 100D;
	
	public static Point project(Point3D point, Point3D camera) {
		Matrix<Double> pointMatrix = Matrices.convertFromPoint3D(point);
		Matrix<Double> cameraMatrix = Matrices.convertFromPoint3D(camera);
		
		Matrix<Double> dMatrix = Matrices.subtract(pointMatrix, cameraMatrix);
		
		double ratio = camera.z / dMatrix.getValue(3);
		
		double x = (ratio * dMatrix.getValue(1)) - camera.x;
		double y = (ratio * dMatrix.getValue(2)) - camera.y;
		
		return new Point((int) x, (int) y);
		
	}

}
