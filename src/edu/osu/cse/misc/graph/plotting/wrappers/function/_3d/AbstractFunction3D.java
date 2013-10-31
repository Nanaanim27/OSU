package edu.osu.cse.misc.graph.plotting.wrappers.function._3d;

import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.graph._3d.Coordinate3D;

/**
 * A function in 3D space 
 */
public abstract class AbstractFunction3D {

	protected boolean isValid = true;
	
	//value1 = x
	protected float value1Min = Float.NEGATIVE_INFINITY, value2Min = Float.NEGATIVE_INFINITY;
	//value2 = y
	protected float value1Max = Float.POSITIVE_INFINITY, value2Max = Float.POSITIVE_INFINITY;
	
	public abstract Coordinate3D evaluateAt(float value1, float value2);
	public abstract String getParameter1();
	public abstract String getParameter2();
	
	/**
	 * Evaluates this function between the given range inclusively, respective to its respective variable.
	 * 
	 * @param value1Min The lower bound of the x-variable for this function.
	 * @param value1Max The upper bound of the x-variable for this function.
	 * @param value2Min The lower bound of the y-variable for this function.
	 * @param value2Max The upper bound of the y-variable for this function.
	 * @param accuracy Simply how many points to plot. An accuracy of 0.1 will plot more points than an accuracy of 
	 * @return
	 */
	public Coordinate3D[] evaluateFrom(float value1Min, float value1Max, float value2Min, float value2Max, float accuracy) {
		LinkedList<Coordinate3D> coords = new LinkedList<>();
		for (float f1 = value1Min, f2 = value2Min; f1 <= value1Max && f2 <= value2Max; f1 += accuracy, f2 += accuracy) {
			Coordinate3D coord = this.evaluateAt(f1, f2);
			if (coord.getX() <= this.value1Max && coord.getX() >= this.value1Min && coord.getY() <= this.value2Max && coord.getY() >= this.value2Min) {
				coords.add(coord);
			}
		}
		return coords.toArray(new Coordinate3D[coords.size()]);
	}
	
	/**
	 * Evaluates this function with a default accuracy of 0.1
	 * 
	 * @see {{@link #evaluateFrom(float, float, float)} 
	 */
	public Coordinate3D[] evaluateFrom(float value1Min, float value1Max, float value2Min, float value2Max) {
		return this.evaluateFrom(value1Min, value1Max, value2Min, value2Max, 0.1f);
	}
	
	/**
	 * Restricts this function to only be evaluated with the given x-y range.
	 * <br />For example, f(x, y)=x^2 + y^2 while restricted from 0 to Float.POSITIVE_INFINITY would show only
	 * the right half of the parabolic shape.
	 * 
	 * @param value1Min The min value of the x-variable.
	 * @param value1Max The max value of the x-variable.
	 * @param value2Min The min value of the y-variable.
	 * @param value2Max The max value of the y-variable.
	 * @return This function.
	 */
	public AbstractFunction3D restrict(float value1Min, float value1Max, float value2Min, float value2Max) {
		this.value1Min = value1Min;
		this.value1Max = value1Max;
		this.value2Min = value2Min;
		this.value2Max = value2Max;
		return this;
	}
	
	/**
	 * Tests this functions continuity at x=0, y=0.
	 * <br />This call should not be made if it is known that the function is not continuous at x=0, y=0
	 * 
	 * @return <tt>true</tt> if the function does not throw an exception when evaluated at x=0, y=0, <tt>false</tt> otherwise.
	 */
	public boolean ensureValidity() {
		try {
			this.evaluateAt(0f, 0f);
		} catch (Exception e) {
			//isValid is triggered false up higher in the event of an exception
		}
		return this.isValid;
	}

	/**
	 * Whether or not this is a well-formed function that is able to be graphed.
	 * 
	 * @return <tt>true</tt> if this function is graphable, <tt>false</tt> otherwise.
	 */
	public boolean isValid() {
		return this.isValid;
	}
}
