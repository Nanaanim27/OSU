package edu.osu.cse.misc.graph.plotting.wrappers.function._2d;

import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

/**
 * A function in a 2D Cartesian coordinate system.
 */
public abstract class AbstractFunction2D {

	public abstract Coordinate2D evaluateAt(float value);
	public abstract String getParameter();
	
	protected boolean isValid = true;
	protected float valueMin = Float.NEGATIVE_INFINITY, valueMax = Float.POSITIVE_INFINITY;

	/**
	 * Evaluates this function between the given range inclusively, respective to its respective variable.
	 * 
	 * @param valueMin The lower bound of this function.
	 * @param valueMax The upper bound of this function
	 * @param accuracy Simply how many points to plot. An accuracy of 0.1 will plot 200 points in a range of -10 to 10. The lower this value is, the more accurate, and resource-intensive, this plot will be.
	 * @return An array of Coordinate2D objects which can be plotted on a Graph.
	 */
	public Coordinate2D[] evaluateFrom(float valueMin, float valueMax, float accuracy) {
		LinkedList<Coordinate2D> coords = new LinkedList<>();
		for (float d = valueMin; d <= valueMax; d += accuracy) {
			Coordinate2D coord = this.evaluateAt(d);
			if (coord.getX() <= this.valueMax && coord.getX() >= this.valueMin) {
				coords.add(coord);
			}
		}
		return coords.toArray(new Coordinate2D[coords.size()]);
	}

	/**
	 * Evaluates this function with a default accuracy of 0.1
	 * 
	 * @see {{@link #evaluateFrom(float, float, float)} 
	 */
	public Coordinate2D[] evaluateFrom(float valueMin, float valueMax) {
		return this.evaluateFrom(valueMin, valueMax, 0.1f);
	}

	/**
	 * Restricts this function to only be evaluated within the given range.
	 * <br />For example, f(x)=x^2 while restricted from 0 to Float.POSITIVE_INFINITY would show only 
	 * the right half of its parabola.
	 * 
	 * @param valueMin The min variable value to use
	 * @param valueMax The max variable value to use
	 * @return This function
	 */
	public AbstractFunction2D restrict(float valueMin, float valueMax) {
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		return this;
	}
	
	/**
	 * Tests this functions continuity at x=0.
	 * <br />This call should not be made if it is known that the function is not continuous at x=0.
	 * 
	 * @return <tt>true</tt> if the function does not throw an exception when evaluated at x=0, <tt>false</tt> otherwise.
	 */
	public boolean ensureValidity() {
		try {
			this.evaluateAt(0f);
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
