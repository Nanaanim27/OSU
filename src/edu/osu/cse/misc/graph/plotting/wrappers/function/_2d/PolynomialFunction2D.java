package edu.osu.cse.misc.graph.plotting.wrappers.function._2d;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import edu.osu.cse.misc.graph.plotting.util.Strings;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

/**
 * A two dimensional function in terms of "x".
 */
public class PolynomialFunction2D extends AbstractFunction2D {

	protected String function;
	protected String parameter = "x";

	public PolynomialFunction2D(String function) {
		this.function = Strings.checkFormula(function);
	}

	@Override
	public Coordinate2D evaluateAt(float value) {
		try {
			ExpressionBuilder builder = new ExpressionBuilder(this.function).withVariable(this.getParameter(), value);
			return new Coordinate2D(value, (float) builder.build().calculate());
		} catch (UnparsableExpressionException | UnknownFunctionException err) {
			if (err instanceof UnparsableExpressionException) {
				this.isValid = false;
			}
			return null;
		}
	}

	/**
	 * Changes the parameter in which this function uses.
	 * <br />Typically used in Parametric Functions to switch to "t".
	 * 
	 * @param parameter The new parameter to use.
	 * @return This function for easy instantiation.
	 */
	public PolynomialFunction2D overrideParameter(String parameter) {
		this.parameter = parameter;
		return this;
	}

	@Override
	public String getParameter() {
		return this.parameter;
	}
	
	@Override
	public PolynomialFunction2D restrict(float valueMin, float valueMax) {
		return (PolynomialFunction2D) super.restrict(valueMin, valueMax);
	}
	
	@Override
	public String toString() {
		return this.function;
	}

}
