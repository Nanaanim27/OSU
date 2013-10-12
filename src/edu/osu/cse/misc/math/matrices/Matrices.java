package edu.osu.cse.misc.math.matrices;

import java.awt.Point;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.math.lang.MatrixArithmeticException;

/**
 * Calculation and conversion methods for numerical-based matrices. 
 */
public class Matrices {

	/**
	 * Adds the values of m2 to m1.
	 * <br />This operation is Commutative, so the order of the matrices is negligible.
	 * 
	 * @param m1 A Matrix of Numbers
	 * @param m2 A Matrix of Numbers
	 * @return A new Matrix representing the sum of each provided matrices.
	 */
	public static <T extends Number> Matrix<T> add(Matrix<T> m1, Matrix<T> m2) {
		try {
			MatrixDimension dim1 = m1.getDimensions();
			MatrixDimension dim2 = m2.getDimensions();
			if (!dim1.equals(dim2)) {
				throw new MatrixArithmeticException("The dimensions of m1 and m2 are not equal: " + dim1+ " and " + dim2);
			}
			Matrix<T> mtx = new Matrix<>(dim1);
			for (int row = 1; row <= dim1.getRowCount(); row++) {
				for (int column = 1; column <= dim1.getColumnCount(); column++) {
					Number m1Value = m1.getValue(row, column);
					Number m2Value = m2.getValue(row, column);
					if (m1Value instanceof Double || m1Value instanceof Float) {
						double newValue = m1Value.doubleValue() + m2Value.doubleValue();
						mtx.setValue(row, column, (T) new Double(newValue));
					}
					else {
						long newValue = m1Value.longValue() + m2Value.longValue();
						mtx.setValue(row, column, (T) new Long(newValue));
					}
				}
			}
			return mtx;
		} catch (MatrixArithmeticException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Subtracts the values of m2 from m1
	 * <br />This operation is <b>NOT</b> Commutative, so the order of the matrices is <b>NOT</b> negligible.
	 * 
	 * @param m1 A Matrix of Numbers
	 * @param m2 A Matrix of Numbers
	 * @return A new Matrix representing the difference of each provided matrices.
	 */
	public static <T extends Number> Matrix<T> subtract(Matrix<T> m1, Matrix<T> m2) {
		try {
			MatrixDimension dim1 = m1.getDimensions();
			MatrixDimension dim2 = m2.getDimensions();
			if (!dim1.equals(dim2)) {
				throw new MatrixArithmeticException("The dimensions of m1 and m2 are not equal: " + dim1+ " and " + dim2);
			}
			Matrix<T> mtx = new Matrix<>(dim1);
			for (int row = 1; row <= dim1.getRowCount(); row++) {
				for (int column = 1; column <= dim1.getColumnCount(); column++) {
					Number m1Value = m1.getValue(row, column);
					Number m2Value = m2.getValue(row, column);
					if (m1Value instanceof Double || m1Value instanceof Float) {
						double newValue = m1Value.doubleValue() - m2Value.doubleValue();
						mtx.setValue(row, column, (T) new Double(newValue));
					}
					else {
						long newValue = m1Value.longValue() + m2Value.longValue();
						mtx.setValue(row, column, (T) new Long(newValue));
					}
				}
			}
			return mtx;
		} catch (MatrixArithmeticException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Multiplies the two given matrices together.
	 * <br />Requires that the dimension <tt>n</tt> be equal if m1 hold
	 *  the dimension <tt>m</tt> x <tt>n</tt> and m2 holds the dimension
	 *  <tt>n</tt> x <tt>p</tt>
	 * 
	 * @param m1 A Matrix of Numbers
	 * @param m2 A Matrix of Numbers
	 * @return A new Matrix of the same Number type representing the product of the two provided matrices.
	 */
	public static <T extends Number> Matrix<T> multiply(Matrix<T> m1, Matrix<T> m2) {
		try {
			MatrixDimension dim1 = m1.getDimensions();
			MatrixDimension dim2 = m2.getDimensions();
			if (dim1.getColumnCount() != dim2.getRowCount()) {
				throw new MatrixArithmeticException("The dimensions of m1 and m2 are not valid: " + dim1+ " and " + dim2);
			}
			Matrix<T> mtx = new Matrix<>(dim1.getRowCount(), dim2.getColumnCount());

			//Iterate through each slot of the new matrix
			for (int row = 1; row <= mtx.getDimensions().getRowCount(); row++) {
				for (int column = 1; column <= mtx.getDimensions().getColumnCount(); column++) {
					Matrix<T> m1Sub = m1.getMatrixVector(row, 0);
					Matrix<T> m2Sub = m2.getMatrixVector(0, column);

					//Calculate the value that will be placed in it by taking the Dot of each sub MatrixVector
					T totalValue = null;
					for (int i = 1; i <= m1Sub.getDimensions().getColumnCount(); i++) {
						T leftValue = m1Sub.getValue(1, i);
						T rightValue = m2Sub.getValue(i, 1);
						if (leftValue instanceof Double || leftValue instanceof Float) {
							if (totalValue == null)
								totalValue = (T) new Double(0);
							double orig = totalValue.doubleValue();
							orig += (leftValue.doubleValue() * rightValue.doubleValue());
							totalValue = (T) new Double(orig);
						}
						else {
							if (totalValue == null)
								totalValue = (T) new Long(0);
							long orig = totalValue.longValue();
							orig += (leftValue.longValue() * rightValue.longValue());
							totalValue = (T) new Long(orig);
						}
					}
					mtx.setValue(row, column, totalValue);
				}
			}

			return mtx;
		} catch (MatrixArithmeticException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Multiplies the given matrix of Numbers by the given scaler value.
	 * 
	 * @param m1 A Matrix of Numbers
	 * @param scale A Number to scale by.
	 * @return A new Matrix of the scaled values.
	 */
	public static <T extends Number> Matrix<T> multiply(Matrix<T> m1, T scale) {
		System.out.println("Multiplying the following matrix by " + scale + ":");
		System.out.println(m1);
		Matrix<T> mtx = new Matrix<>(m1.getDimensions());
		for (int i = 1; i <= mtx.size(); i++) {
			T orig = m1.getValue(i);
			if (orig instanceof Double || orig instanceof Float) {
				orig = (T) new Double(orig.doubleValue() * scale.doubleValue());
			}
			else {
				orig = (T) new Long((long) (orig.longValue() * scale.doubleValue()));
			}
			mtx.setValue(i, orig);
		}
		return mtx;
	}
	
	/**
	 * Divides the given matrix of Numbers by the given scaler value.
	 * 
	 * @param m1 A Matrix of Numbers
	 * @param scale A Number to scale by.
	 * @return A new Matrix of the scaled values.
	 */
	public static <T extends Number> Matrix<T> divide(Matrix<T> m1, T scale) {
		return multiply(m1, (T) new Double(1/scale.doubleValue()));
	}

	/**
	 * Inverts each value of the given Matrix.
	 * <br />Mathematically, multiplies each Number element of the Matrix by -1.
	 * 
	 * @param m1 A Matrix of Numbers
	 * @return A new Matrix representing the inverted values.
	 */
	public static <T extends Number> Matrix<T> invert(Matrix<T> m1) {
		return multiply(m1, (T) new Integer(-1));
	}
	
	/**
	 * Creates a square matrix with 1's on the main diagonal and 0's elsewhere.
	 * <br />
	 * For example, createIdentityMatrix(4) would result in the following:<pre>
	 * | 1 0 0 0 |
	 * | 0 1 0 0 |
	 * | 0 0 1 0 |
	 * | 0 0 0 1 |
	 *</pre>
	 * @param size The row and column count of the Matrix to be created.
	 * @return A new <a href="http://en.wikipedia.org/wiki/Identity_matrix">Identity Matrix</a> with the dimension size x sizes
	 */
	public static Matrix<Integer> createIdentityMatrix(int size) {
		Matrix<Integer> mtx = new Matrix<>(size, size);
		for (int row = 1; row <= mtx.getDimensions().getRowCount(); row++) {
			for (int column = 1; column <= mtx.getDimensions().getRowCount(); column++) {
				if (row == column)
					mtx.setValue(row, column, 1);
				else
					mtx.setValue(row, column, 0);
			}
		}
		return mtx;
	}
	
	/**
	 * Converts a Point3D object into its respective Matrix of size 3x1.
	 * 
	 * @param point A Point3D object
	 * @return A new Matrix of size 3x1 containing the values from the given Point3D.
	 */
	public static Matrix<Double> convertFromPoint3D(Point3D point) {
		Matrix<Double> mtx = new Matrix<>(3, 1);
		mtx.setValue(1, point.x);
		mtx.setValue(2, point.y);
		mtx.setValue(3, point.z);
		return mtx;
	}

	/**
	 * Converts a Point object into its respective Matrix of size 2x1.
	 * 
	 * @param point A Point object
	 * @return A new Matrix of size 2x1 containing the values from the given Point.
	 */
	public static Matrix<Integer> convertFromPoint2D(Point point) {
		Matrix<Integer> mtx = new Matrix<>(2, 1);
		mtx.setValue(1, point.x);
		mtx.setValue(2, point.y);
		return mtx;
	}

	/**
	 * Converts a 3x1 matrix into a Point3D object. The association is as follows:<pre>
	 * | x |
	 * | y | --> new Point3D(x, y, z)
	 * | z |
	 * 
	 * @param m1 A Matrix of Integers
	 * @return A Point3D object with the proper associated values as described above.
	 */
	public static Point3D convertToPoint3D(Matrix<Double> m1) {
		if (m1.getDimensions().getRowCount() != 3 && m1.getDimensions().getColumnCount() != 1) {
			System.err.println("Invalid matrix dimensions: " + m1.getDimensions());
			return null;
		}
		return new Point3D(m1.getValue(1), m1.getValue(2), m1.getValue(3));
	}

	public static void main(String[] args) {
		Integer[][] d1 = new Integer[][] {
				{ 1, 2, 5, 3 },
				{ 2, 4, 1, 2 },
				{ 3, 2, 8, 1 },
				{ 0, 1, 0, 10 }
		};
		
		Integer[][] d2 = new Integer[][] {
				{ 10, 0 },
		};
		
		Matrix<Integer> m1 = new Matrix<>(d1), m2 = new Matrix<>(d2);
		
		System.out.println(m1.contains(m2));
		
	}

}
