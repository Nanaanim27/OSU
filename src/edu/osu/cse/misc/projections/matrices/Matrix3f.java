package edu.osu.cse.misc.projections.matrices;


public class Matrix3f {

	private final float[][] data;

	/**
	 * Creates a new identity matrix of size 3x3. 
	 */
	public Matrix3f() {
		this(new float[] { 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f });
	}
	
	/**
	 * Creates a new matrix with the given values as laid out below:<pre>
	 * | values[0], values[1], values[2] |
	 * | values[3], values[4], values[5] |
	 * | values[6], values[7], values[8] |</pre>
	 * 
	 * @param values An array of floating point values.
	 */
	public Matrix3f(float[] values) {
		if (values.length != 9) {
			throw new IllegalArgumentException("Must provide 9 values to fill the 3x3 matrix.");
		}
		this.data = new float[3][3];
		this.setValues(values);
	}
	
	/**
	 * Creates a new matrix as laid out by the given 2D array of floating point values.
	 * 
	 * @param values A Two-Dimensional array of floating point values.
	 */
	public Matrix3f(float[][] values) {
		this();
		if (values.length != 3 || values[0].length != 3 || values[1].length != 3 || values[2].length != 3) {
			throw new IllegalArgumentException("Must provide a 3x3 array of values to fill the 3x3 matrix.");
		}
		this.setValues(values);
	}

	/**
	 * Re-constructs this matrix as described in {@link #Matrix3f(float[])}
	 * 
	 * @param values An array of floating point values.
	 */
	public void setValues(float[] values) {
		int index = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				this.data[r][c] = values[index++];
			}
		}
	}
	
	/**
	 * Re-constructs this matrix as described in {@link #Matrix3f(float[][])}
	 * 
	 * @param values A Two-Dimensional array of floating point values.
	 */
	public void setValues(float[][] values) {
		this.setValues(new float[] { values[0][0], values[0][1], values[0][2], values[1][0], values[1][1], values[1][2], values[2][0], values[2][1], values[2][2] });
	}

	

}
