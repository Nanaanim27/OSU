package edu.osu.cse.misc.graph.plotting.wrappers.function._3d;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import edu.osu.cse.misc.graph.plotting.util.Strings;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._3d.Coordinate3D;

public class PolynomialFunction3D extends AbstractFunction3D {

	protected String function;
	protected String parameter1 = "x", parameter2 = "y";
	
	public PolynomialFunction3D(String function) {
		this.function = Strings.checkFormula(function);
	}
	
	@Override
	public Coordinate3D evaluateAt(float value1, float value2) {
		try {
			ExpressionBuilder builder = new ExpressionBuilder(this.function).withVariable(this.getParameter1(), value1).withVariable(this.getParameter2(), value2);
			return new Coordinate3D(value1, value2, (float) builder.build().calculate());
		} catch (UnparsableExpressionException | UnknownFunctionException err) {
			if (err instanceof UnparsableExpressionException) {
				this.isValid = false;
			}
			return null;
		}
	}
	
	/**
	 * Changes the parameters in which this function uses.
	 * <br />Typically used in Parametric Functions to switch to "t".
	 * 
	 * @param parameter1 The new parameter to use for the first parameter.
	 * @param parameter1 The new parameter to use for the second parameter.
	 * @return This function for easy instantiation.
	 */
	public PolynomialFunction3D overrideParameters(String parameter1, String parameter2) {
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		return this;
	}
	
	@Override
	public String getParameter1() {
		return this.parameter1;
	}
	
	@Override
	public String getParameter2() {
		return this.parameter2;
	}
	
	@Override
	public PolynomialFunction3D restrict(float value1Min, float value1Max, float value2Min, float value2Max) {
		return (PolynomialFunction3D) super.restrict(value1Min, value1Max, value2Min, value2Max);
	}
	
	@Override
	public String toString() {
		return this.function;
	}
}
