package edu.osu.cse.misc.graph.plotting._3d;

import java.awt.Point;
import java.util.LinkedList;

import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;

public class Projections {

	public static void main(String[] args) {
		Point3D p = new Point3D(4, 5, 3);
	}
	
	public static Point convert3Dto2D(Point3D point) {
		
		Matrix<Double> scaleMatrix = new Matrix<>(new Double[][] { {10D, 0D, 0D}, {0D, 0D, 10D} });
		Matrix<Double> pointMatrix = new Matrix<>(new Double[][] { {point.x}, {point.y}, {point.z} });
		Matrix<Double> offsetMatrix = new Matrix<>(new Double[][] { {0D}, {0D} });
		
		Matrix<Double> scaled = Matrices.multiply(scaleMatrix, pointMatrix);
		Matrix<Double> result = Matrices.add(scaled, offsetMatrix);
		
		return new Point((int) result.getValue(1, 1).doubleValue(), (int) result.getValue(2, 1).doubleValue());
	}
	
	private static Point3D[] generateField(int numTiles) {
		
		int sideLength = 750;
		int jump = sideLength / numTiles;
		
		LinkedList<Point3D> points = new LinkedList<>();
		for (int x = -sideLength/2; x <= sideLength/2; x+= jump) {
			for (int z = -sideLength/2; z <= sideLength/2; z+= jump) {
				
			}
		}
		return points.toArray(new Point3D[points.size()]);
		
	}
	
}
