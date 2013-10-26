package edu.osu.cse.misc.math.matrices;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Represents a 2D array of T values that can be accessed and manipulated.
 */
public class Matrix<T extends Number> implements Iterable<T> {

	private final MatrixDimension dimensions;
	private final T[][] data;

	public Matrix(MatrixDimension dimensions) {
		this.dimensions = dimensions;
		this.data = (T[][]) new Number[this.dimensions.getRowCount()][this.dimensions.getColumnCount()];
	}

	public Matrix(int rows, int columns) {
		this(new MatrixDimension(rows, columns));
	}

	public Matrix(T[][] data) {
		this(data.length, data[0].length);
		this.setData(data);
	}

	/**
	 * Returns a single row or column Matrix that is limited to the values of the given row or column.
	 * <br />This method requires that row OR column > 0, but not both.
	 * <br /><br />This method assumes the first row/column to hold an index of 1.
	 * 
	 * @param row A row number.
	 * @param column A column number.
	 * @return A Matrix representing the row/column vector from the provided information.
	 */
	public Matrix<T> getMatrixVector(int row, int column) {
		if (row <= 0 ^ column <= 0) {
			boolean rowVector = row > 0;
			//If rowVector, 1 x columnCount. Else, rowCount x 1
			Matrix<T> mtx = new Matrix<>(rowVector ? 1 : this.getDimensions().getRowCount(), rowVector ? this.getDimensions().getColumnCount() : 1);
			T[] values = (T[]) new Number[rowVector ? this.getDimensions().getColumnCount() : this.getDimensions().getRowCount()];

			int index = 0;
			for (int r = 1; r <= this.getDimensions().getRowCount(); r++) {
				for (int c = 1; c <= this.getDimensions().getColumnCount(); c++) {
					if ((row != -1 && row == r) || (column != -1 && column == c)) {
						values[index++] = this.getValue(r, c);
					}
				}
			}
			T[][] data = (T[][]) new Number[rowVector ? 1 : values.length][rowVector ? values.length : 1];
			for (int i = 0; i < values.length; i++) {
				data[rowVector ? 0 : i][rowVector ? i : 0] = values[i];
			}

			mtx.setData(data);
			return mtx;
		}
		System.err.println("Row or Column must be 0. Only one value can be non-zero.");
		return null;
	}

	/**
	 * Fetches the value at the given row and column.
	 * <br /><tt>row</tt> and <tt>column</tt> hold values [1, length] inclusively and respective to rows and columns.
	 * 
	 * @param row A row
	 * @param column A column
	 * @return The value at the provided row and column.
	 */
	public T getValue(int row, int column) {
		row--; column--;
		return this.data[row][column];
	}

	/**
	 * Fetches the value at the given index as represented by {@link #toList()}
	 * 
	 * @param index An index
	 * @return The value at the provided index;
	 */
	public T getValue(int index) {
		return this.getValue(this.getRow(index), this.getColumn(index));
	}

	/**
	 * Sets the value at the given row and column to the provided value.
	 * 
	 * @param row A row
	 * @param column A column
	 * @param data The value to assign to the given row and column.
	 * @return The previous value contained at the given row and column.
	 */
	public T setValue(int row, int column, T data) {
		T currentData = this.getValue(row, column);
		row--; column--;
		this.data[row][column] = data;
		return currentData;
	}

	/**
	 * Sets the value at the given index to the provided value.
	 * 
	 * @param index An index
	 * @param data The value to assign to the given row and column.
	 * @return The previous value contained at the given index.
	 */
	public T setValue(int index, T data) {
		return this.setValue(this.getRow(index), this.getColumn(index), data);
	}

	/** Sets this Matrix's data to the given data */
	public void setData(T[][] data) {
		if (this.getDimensions().getRowCount() != data.length || this.getDimensions().getColumnCount() != data[0].length) {
			return;
		}
		for (int row = 1; row <= this.getDimensions().getRowCount(); row++) {
			for (int column = 1; column <= this.getDimensions().getColumnCount(); column++) {
				this.setValue(row, column, data[row-1][column-1]);
			}
		}
	}

	/** The row represented by the given index. For example:<pre>
	 * | 1 3 9 | index 3 represents '9', which is row 1
	 * | 5 8 1 | index 5 represents '8', which is row 2
	 * | 2 1 6 | index 9 represents '6', which is row 3
	 * @param index The given index to find
	 * @returns The row number of the given index.
	 */
	private int getRow(int index) {
		return ((int) Math.ceil(index / (double) this.getDimensions().getColumnCount()));
	}

	/** The column represented by the given index. For example:<pre>
	 * | 1 3 9 | index 3 represents '9', which is column 3
	 * | 5 8 1 | index 5 represents '8', which is row 2
	 * | 2 1 6 | index 9 represents '6', which is row 3
	 * @param index The given index to find
	 * @returns The column number of the given index.
	 */
	private int getColumn(int index) {
		int modulo = index % this.getDimensions().getColumnCount();
		return modulo == 0 ? this.getDimensions().getColumnCount() : modulo;
	}

	/**
	 * The dimensions of this Matrix
	 * 
	 * @return The MatrixDimension representing the dimensions of this Matrix.
	 */
	public MatrixDimension getDimensions() {
		return this.dimensions;
	}

	/**
	 * The number of elements in this Matrix.
	 * 
	 * @return The number of elements in this Matrix.
	 */
	public int size() {
		MatrixDimension dims = this.getDimensions();
		return dims.getRowCount() * dims.getColumnCount();
	}

	/**
	 * Checks whether or not the provided Matrix is a subspace of <tt>this</tt> Matrix. <pre>
	 * For example, the following Matrix:
	 * 
	 * | 2 3 |
	 * | 1 1 |
	 * 
	 * is a subset of:
	 * 
	 * | 2 1 3 3 |
	 * | 5 2 3 0 |
	 * | 6 1 1 2 |
	 * | 1 4 2 3 |
	 * </pre>
	 * @param m1 A Matrix of Numbers
	 * @return <tt>true</tt> if the provided Matrix is a subspace of <tt>this</tt> Matrix as described above. <tt>false</tt> otherwise.
	 */
	public boolean contains(Matrix<T> m1) {
		if (m1 == null || this.size() < m1.size()) {
			return false;
		}

		MatrixDimension thisDims = this.getDimensions(), otherDims = m1.getDimensions();

		int thisRowCount = thisDims.getRowCount(), thisColumnCount = thisDims.getColumnCount();
		int otherRowCount = otherDims.getRowCount(), otherColumnCount = otherDims.getColumnCount();

		for (int row = 1; row <= thisRowCount; row++) {
			inner:
				for (int column = 1; column <= thisColumnCount; column++) {

					int remainingRows = thisRowCount - row + 1;
					int remainingColumns = thisColumnCount - column + 1;
					if (otherRowCount > remainingRows || row == thisRowCount && otherColumnCount > remainingColumns) {
						//The provided Matrix m1 is larger than the remaining elements in this Matrix
						return false;
					}

					if (this.getValue(row, column).equals(m1.getValue(1, 1))) {
						for (int thisRow = row, otherRow = 1; thisRow <= thisRowCount && otherRow <= otherRowCount; thisRow++, otherRow++) {
							for (int thisColumn = column, otherColumn = 1; thisColumn <= thisColumnCount && otherColumn <= otherColumnCount; thisColumn++, otherColumn++) {
								if (!this.getValue(thisRow, thisColumn).equals(m1.getValue(otherRow, otherColumn))) {
									//Mismatch, continue with the next element in this Matrix
									continue inner;
								}
							}
						}
						//Didn't find any mismatches == a full subspace
						return true;
					}
				}	
		}
		//Reached the end of this Matrix with no matches to be found
		return false;
	}

	/**
	 * Transforms this Matrix into a Single Dimensional list. <pre>
	 * | A1  A2  A3 |
	 * | A4  A5  A6 |
	 * | A7  A8  A9 |
	 * 
	 * ...converts to
	 * 
	 * [ A1, A2, A3, A4, A5, A6, A7, A8, A9 ]
	 * </pre>
	 * 
	 * @return A LinkedList containing the elements of this Matrix.
	 */
	public LinkedList<T> toList() {
		LinkedList<T> list = new LinkedList<>();
		for (int row = 1; row <= this.getDimensions().getRowCount(); row++) {
			for (int column = 1; column <= this.getDimensions().getColumnCount(); column++) {
				list.add(this.getValue(row, column));
			}	
		}
		return list;
	}

	/**
	 * Checks whether this Matrix has square dimensions or not.
	 * 
	 * @return <tt>true</tt> if this Matrix's row count is the same as its column count. <tt>false</tt> otherwise.
	 */
	public boolean isSquare() {
		return this.getDimensions().getRowCount() == this.getDimensions().getColumnCount();
	}

	/**
	 * Checks whether this Matrix is <a href="http://en.wikipedia.org/wiki/(0,1)-matrix">binary</a> or not.
	 * 
	 * @return <tt>true</tt> if this matrix contains only binary values. <tt>false</tt> otherwise.
	 */
	public boolean isBinary() {
		for (Number value : this) {
			double dValue = value.doubleValue();
			if (dValue != 0 && dValue != 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String mtx = "";
		for (int r = 1; r <= this.getDimensions().getRowCount(); r++) {
			mtx += "";
			for (int c = 1; c <= this.getDimensions().getColumnCount(); c++) {
				mtx += this.getValue(r, c) + (c != this.getDimensions().getColumnCount() ? "\t" : "");
			}
			if (r != this.getDimensions().getRowCount())
				mtx += "\n";
		}
		return mtx;
	}

	@Override
	public Iterator<T> iterator() {
		return this.toList().iterator();
	}

}
