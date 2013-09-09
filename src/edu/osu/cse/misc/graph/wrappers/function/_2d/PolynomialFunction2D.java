package edu.osu.cse.misc.graph.wrappers.function._2d;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import edu.osu.cse.misc.graph.util.Strings;
import edu.osu.cse.misc.graph.wrappers.graph.Coordinate2D;

public class PolynomialFunction2D extends AbstractFunction2D {

    protected String function;
    
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

    @Override
    public String getParameter() {
        return "x";
    }
    
}
