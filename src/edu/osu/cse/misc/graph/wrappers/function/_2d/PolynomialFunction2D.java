package edu.osu.cse.misc.graph.wrappers.function._2d;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import edu.osu.cse.misc.graph.util.Strings;
import edu.osu.cse.misc.graph.wrappers.graph.Coordinate2D;

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
	public Coordinate2D evaluateAt(double value) {
		try {
			ExpressionBuilder builder = new ExpressionBuilder(this.function).withVariable(this.getParameter(), value);
			return new Coordinate2D(value, builder.build().calculate());
		} catch (UnparsableExpressionException | UnknownFunctionException err) {
			if (err instanceof UnparsableExpressionException) {
				this.isValid = false;
			}
			err.printStackTrace();
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
	public PolynomialFunction2D restrict(double valueMin, double valueMax) {
		return (PolynomialFunction2D) super.restrict(valueMin, valueMax);
	}

}
