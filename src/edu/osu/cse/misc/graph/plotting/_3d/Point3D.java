package edu.osu.cse.misc.graph.plotting._3d;

import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;
import edu.osu.cse.misc.math.matrices.euler.EulerMatrices;

public class Point3D {

	public float x, y, z;
	
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void rotateAbout(Point3D other, float radsX, float radsY, float radsZ) {
		Matrix<Float> rotationX = EulerMatrices.eulerXMatrix(radsX);
		Matrix<Float> rotationY = EulerMatrices.eulerYMatrix(radsY);
		Matrix<Float> rotationZ = EulerMatrices.eulerZMatrix(radsZ);
		
		Matrix<Float> rotation = Matrices.multiply(rotationX, Matrices.multiply(rotationY, rotationZ));
		Matrix<Float> dMatrix = Matrices.multiply(rotation, Matrices.convertFromPoint3D(this));
		
		this.x = dMatrix.getValue(1);
		this.y = dMatrix.getValue(2);
		this.z = dMatrix.getValue(3);
		
		System.out.println("After rotation: " + this);
	}
	
	/**
	 * Auto-Generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Float.floatToIntBits(this.x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Float.floatToIntBits(this.y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Float.floatToIntBits(this.z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compares this Point to another.
	 * @param obj Another Object to compare this Point to.
	 * @return <tt>true</tt> if the provided Object is a Point, and x, y, and z are equal for each Point. <tt>false</tt> otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point3D)) {
			return false;
		}
		Point3D other = (Point3D) obj;
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
}
