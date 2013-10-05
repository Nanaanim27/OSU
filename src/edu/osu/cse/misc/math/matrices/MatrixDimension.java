package edu.osu.cse.misc.math.matrices;

import java.security.InvalidParameterException;

import edu.osu.cse.misc.math.lang.InvalidMatrixDimensionFormatException;

/**
 * An immutable set of dimensions to represent the size of a Matrix. 
 */
public class MatrixDimension {

	private final int columns, rows;

	public MatrixDimension(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new InvalidParameterException("Rows and column count must both be >= 1.");
		}
		this.rows = rows;
		this.columns = columns;
	}
	
	/**
	 * Creates a MatrixDimension with the same row and column count as the provided MatrixDimension.
	 * 
	 * @param dim A valid MatrixDimension object.
	 */
	public MatrixDimension(MatrixDimension dim) {
		this(dim.rows, dim.columns);
	}

	/**
	 * The number of rows represented by this MatrixDimension.
	 * 
	 * @return An integer value representing the number of rows in this MatrixDimension
	 */
	public int getRowCount() {
		return this.rows;
	}

	/**
	 * The number of columns represented by this MatrixDimension.
	 * 
	 * @return An integer value representing the number of columns in this MatrixDimension
	 */
	public int getColumnCount() {
		return this.columns;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MatrixDimension) {
			MatrixDimension other = (MatrixDimension) obj;
			return other.rows == this.rows && other.columns == this.columns;
		}
		return false;
	}
	
	@Override
	/**
	 * Auto-Generated 
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.columns;
		result = prime * result + this.rows;
		return result;
	}

	@Override
	public String toString() {
		return "[" + this.rows + "x" + this.columns + "]";
	}
	
	/**
	 * Parses a AxB matrix format into a <tt>MatrixDimension</tt> object.
	 * 
	 * @param dim A String representing the dimensions of a Matrix.
	 * @return A MatrixDimension that matches the given String.
	 */
	public static MatrixDimension valueOf(String dim) {
		String[] dimensions = dim.toLowerCase().split("x");
		try {
			try {
				int rows = Integer.parseInt(dimensions[0]);
				int columns = Integer.parseInt(dimensions[1]);
				return new MatrixDimension(rows, columns);
			} catch (NumberFormatException e) {
				throw new InvalidMatrixDimensionFormatException("Invalid matrix dimensions for the given String: " + dim + ". Expected format is AxB");
			}
		} catch (InvalidMatrixDimensionFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

}
