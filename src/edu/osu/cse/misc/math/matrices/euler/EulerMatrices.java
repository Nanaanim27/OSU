package edu.osu.cse.misc.math.matrices.euler;

import edu.osu.cse.misc.math.matrices.Matrix;
import edu.osu.cse.misc.math.trig.TrigTables;

public class EulerMatrices {

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
	
	/**
	 * @return The euler rotation matrix along the x-axis.
	 */
	public static Matrix<Double> eulerXMatrix(int degreesX) {
		eulerXMatrix.setData(new Double[][] {
				{ 1D, 0D, 0D },
				{ 0D, -TrigTables.cos(-degreesX), -TrigTables.sin(-degreesX) },
				{ 0D, TrigTables.sin(-degreesX), TrigTables.cos(-degreesX) }
		});
		return eulerXMatrix;
	}
	
	/**
	 * @return The euler rotation matrix along the y-axis.
	 */
	public static Matrix<Double> eulerYMatrix(int degreesY) {
		eulerYMatrix.setData(new Double[][] {
				{ TrigTables.cos(-degreesY), 0D, TrigTables.sin(-degreesY) },
				{ 0D, 1D, 0D },
				{ -TrigTables.sin(-degreesY), 0D, TrigTables.cos(-degreesY) }
		});
		return eulerYMatrix;
	}
	
	/**
	 * @return The euler rotation matrix along the z-axis.
	 */
	public static Matrix<Double> eulerZMatrix(int degreesZ) {
		eulerZMatrix.setData(new Double[][] {
				{ TrigTables.cos(-degreesZ), -TrigTables.sin(-degreesZ), 0D },
				{ TrigTables.sin(-degreesZ), TrigTables.cos(-degreesZ), 0D },
				{ 0D, 0D, 1D }
		});
		return eulerZMatrix;
	}

	public static Matrix<Double> eulerXMatrix(double radsX) {
		return eulerXMatrix((int) Math.toDegrees(radsX));
	}
	
	public static Matrix<Double> eulerYMatrix(double radsY) {
		return eulerYMatrix((int) Math.toDegrees(radsY));
	}
	
	public static Matrix<Double> eulerZMatrix(double radsZ) {
		return eulerZMatrix((int) Math.toDegrees(radsZ));
	}
	
}
