package edu.osu.cse.misc.math.matrices;


/**
 * Represents a 2D array of T values that can be accessed and manipulated.
 */
public class Matrix<T> {

	private final MatrixDimension dimensions;
	private final T[][] data;
	
	public Matrix(MatrixDimension dimensions) {
		this.dimensions = dimensions;
		this.data = (T[][]) new Object[this.dimensions.getRowCount()][this.dimensions.getColumnCount()];
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
			T[] values = (T[]) new Object[rowVector ? this.getDimensions().getColumnCount() : this.getDimensions().getRowCount()];
			
			int index = 0;
			for (int r = 1; r <= this.getDimensions().getRowCount(); r++) {
				for (int c = 1; c <= this.getDimensions().getColumnCount(); c++) {
					if ((row != -1 && row == r) || (column != -1 && column == c)) {
						values[index++] = this.getValue(r, c);
					}
				}
			}
			T[][] data = (T[][]) new Object[rowVector ? 1 : values.length][rowVector ? values.length : 1];
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
	
	private void setData(T[][] data) {
		for (int row = 1; row <= this.getDimensions().getRowCount(); row++) {
			for (int column = 1; column <= this.getDimensions().getColumnCount(); column++) {
				this.setValue(row, column, data[row-1][column-1]);
			}
		}
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
	 * Checks whether this Matrix has square dimensions or not.
	 * 
	 * @return <tt>true</tt> if this Matrix's row count is the same as its column count. <tt>false</tt> otherwise.
	 */
	public boolean isSquare() {
		return this.getDimensions().getRowCount() == this.getDimensions().getColumnCount();
	}
	
	@Override
	public String toString() {
		String mtx = "";
		for (int r = 1; r <= this.getDimensions().getRowCount(); r++) {
			mtx += "";
			for (int c = 1; c <= this.getDimensions().getColumnCount(); c++) {
				mtx += this.getValue(r, c) + (c != this.getDimensions().getColumnCount() ? "\t" : "");
			}
			mtx += "\n";
		}
		return mtx;
	}
}
