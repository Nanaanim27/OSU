package edu.osu.cse.misc.graph.wrappers.function._2d;

import java.util.LinkedList;

import edu.osu.cse.misc.graph.wrappers.graph.Coordinate2D;

/**
 * A function in a 2D Cartesian coordinate system.
 */
public abstract class AbstractFunction2D {

	public abstract Coordinate2D evaluateAt(double value);
	public abstract String getParameter();
	protected boolean isValid = true;

	/**
	 * Evaluates this function between the given range inclusively respective to its respective variable.
	 * 
	 * @param valueMin The lower bound of this function.
	 * @param valueMax The upper bound of this function
	 * @param accuracy Simply how many points to plot. An accuracy of 0.1 will plot 200 points in a range of -10 to 10. The lower this value is, the more accurate, and resource-intensive, this plot will be.
	 * @return An array of Coordinate2D objects which can be plotted on a Graph.
	 */
	public Coordinate2D[] evaluateFrom(double valueMin, double valueMax, double accuracy) {
		LinkedList<Coordinate2D> coords = new LinkedList<>();
		for (double d = valueMin; d <= valueMax; d += accuracy) {
			coords.add(this.evaluateAt(d));
		}
		return coords.toArray(new Coordinate2D[coords.size()]);
	}

	/**
	 * Evaluates this function with a default accuracy of 0.1
	 * 
	 * @see {{@link #evaluateFrom(double, double, double)} 
	 */
	public Coordinate2D[] evaluateFrom(double valueMin, double valueMax) {
		return this.evaluateFrom(valueMin, valueMax, 0.1D);
	}

	/**
	 * Whether or not this Function is valid or not.
	 * An invalid function will not be drawn and be removed from its respective Graph.
	 * 
	 * @return <tt>true</tt> or <tt>false</tt> whether this Function is valid or not.
	 */
	public boolean isValid() {
		return this.isValid;
	}
}
