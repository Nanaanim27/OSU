package edu.osu.cse.misc.projections;

import java.awt.Point;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;
import edu.osu.cse.misc.math.trig.TrigTables;

public class Projections {

	public static Point project(Point3D point, CoordinateSystem3D camera, CoordinateSystem3D objectPlane) {
		Point3D cameraPoint = camera.getOrigin();
		Point3D objectPoint = camera.getPoint(point, objectPlane);
		
		double rot = objectPlane.getRotation();
		
		Matrix<Double> rotation = Matrices.multiply(RM.eulerX(rot), Matrices.multiply(RM.eulerY(rot), RM.eulerZ(rot)));
		Matrix<Double> dMatrix = Matrices.multiply(rotation, Matrices.subtract(Matrices.convertFromPoint3D(objectPoint), Matrices.convertFromPoint3D(cameraPoint)));
		
		double ratio = cameraPoint.z / dMatrix.getValue(3);
		
		return new Point((int) ((ratio * dMatrix.getValue(1)) - cameraPoint.x), (int) ((ratio * dMatrix.getValue(2)) - cameraPoint.y));
	}
	
	public static class RM {
		
		private static final Matrix<Double> eulerXMatrix = new Matrix<>(new Double[][] {
			{ 1D, 0D, 0D },
			{ 0D, TrigTables.cos(0D), -TrigTables.sin(0D) },
			{ 0D, TrigTables.sin(0D), TrigTables.cos(0D) }
		});
		
		private static final Matrix<Double> eulerYMatrix = new Matrix<>(new Double[][] {
				{ TrigTables.cos(0D), 0D, TrigTables.sin(0D) },
				{ 0D, 1D, 0D },
				{ -TrigTables.sin(0D), 0D, TrigTables.cos(0D) }
			});
			
		private static final Matrix<Double> eulerZMatrix = new Matrix<>(new Double[][] {
				{ TrigTables.cos(0D), -TrigTables.sin(0D), 0D },
				{ TrigTables.sin(0D), TrigTables.cos(0D), 0D },
				{ 0D, 0D, 1D }
			});
			
			
		
		public static Matrix<Double> eulerX(double rads) {
			eulerXMatrix.setValue(5, TrigTables.cos(rads));
			eulerXMatrix.setValue(6, -TrigTables.sin(rads));
			eulerXMatrix.setValue(8, TrigTables.sin(rads));
			eulerXMatrix.setValue(9, TrigTables.cos(rads));
			return eulerXMatrix;
		}
		
		public static Matrix<Double> eulerY(double rads) {
			eulerYMatrix.setValue(1, TrigTables.cos(rads));
			eulerYMatrix.setValue(3, TrigTables.sin(rads));
			eulerYMatrix.setValue(7, -TrigTables.sin(rads));
			eulerYMatrix.setValue(9, TrigTables.cos(rads));
			return eulerYMatrix;
		}
		
		public static Matrix<Double> eulerZ(double rads) {
			eulerZMatrix.setValue(1, TrigTables.cos(rads));
			eulerZMatrix.setValue(2, -TrigTables.sin(rads));
			eulerZMatrix.setValue(4, TrigTables.sin(rads));
			eulerZMatrix.setValue(5, TrigTables.cos(rads));
			return eulerZMatrix;
		}
		
	}
	
}
