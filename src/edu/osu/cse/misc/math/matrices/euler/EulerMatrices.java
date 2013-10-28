package edu.osu.cse.misc.math.matrices.euler;

import edu.osu.cse.misc.math.matrices.Matrix;
import edu.osu.cse.misc.math.trig.TrigTables;

public class EulerMatrices {

	private static final Matrix<Float> eulerXMatrix = new Matrix<>(new Float[][] {
			{ 1f, 0f, 0f },
			{ 0f, TrigTables.cos(0f), -TrigTables.sin(0f) },
			{ 0f, TrigTables.sin(0f), TrigTables.cos(0f) }
	});

	private static final Matrix<Float> eulerYMatrix = new Matrix<>(new Float[][] {
			{ TrigTables.cos(0f), 0f, TrigTables.sin(0f) },
			{ 0f, 1f, 0f },
			{ -TrigTables.sin(0f), 0f, TrigTables.cos(0f) }
	});

	private static final Matrix<Float> eulerZMatrix = new Matrix<>(new Float[][] {
			{ TrigTables.cos(0f), -TrigTables.sin(0f), 0f },
			{ TrigTables.sin(0f), TrigTables.cos(0f), 0f },
			{ 0f, 0f, 1f }
	});
	
	/**
	 * @return The euler rotation matrix along the x-axis.
	 */
	public static Matrix<Float> eulerXMatrix(int degreesX) {
		eulerXMatrix.setData(new Float[][] {
				{ 1f, 0f, 0f },
				{ 0f, -TrigTables.cos(-degreesX), -TrigTables.sin(-degreesX) },
				{ 0f, TrigTables.sin(-degreesX), TrigTables.cos(-degreesX) }
		});
		return eulerXMatrix;
	}
	
	/**
	 * @return The euler rotation matrix along the y-axis.
	 */
	public static Matrix<Float> eulerYMatrix(int degreesY) {
		eulerYMatrix.setData(new Float[][] {
				{ TrigTables.cos(-degreesY), 0f, TrigTables.sin(-degreesY) },
				{ 0f, 1f, 0f },
				{ -TrigTables.sin(-degreesY), 0f, TrigTables.cos(-degreesY) }
		});
		return eulerYMatrix;
	}
	
	/**
	 * @return The euler rotation matrix along the z-axis.
	 */
	public static Matrix<Float> eulerZMatrix(int degreesZ) {
		eulerZMatrix.setData(new Float[][] {
				{ TrigTables.cos(-degreesZ), -TrigTables.sin(-degreesZ), 0f },
				{ TrigTables.sin(-degreesZ), TrigTables.cos(-degreesZ), 0f },
				{ 0f, 0f, 1f }
		});
		return eulerZMatrix;
	}

	public static Matrix<Float> eulerXMatrix(float radsX) {
		return eulerXMatrix((int) Math.toDegrees(radsX));
	}
	
	public static Matrix<Float> eulerYMatrix(float radsY) {
		return eulerYMatrix((int) Math.toDegrees(radsY));
	}
	
	public static Matrix<Float> eulerZMatrix(float radsZ) {
		return eulerZMatrix((int) Math.toDegrees(radsZ));
	}
	
}
