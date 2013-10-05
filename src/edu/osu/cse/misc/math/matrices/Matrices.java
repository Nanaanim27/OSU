package edu.osu.cse.misc.math.matrices;

import edu.osu.cse.misc.math.lang.MatrixArithmeticException;

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





	public static void main(String[] args) {

		Integer[][] data1 = new Integer[][] {
				{ 1, 2, 3 },
				{ 4, 5, 6 }
		};

		Integer[][] data2 = new Integer[][] {
				{ 7, 8 },
				{ 9, 10 },
				{ 11, 12 }
		};
		Matrix<Integer> m1 = new Matrix<>(data1), m2 = new Matrix<>(data2);

		System.out.println(m1);
		System.out.println(m2);
		System.out.println("\n\n");

		System.out.println(multiply(m1, m2));
	}

}
