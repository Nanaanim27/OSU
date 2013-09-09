package edu.osu.cse.misc.graph.wrappers.function._2d;

import edu.osu.cse.misc.graph.wrappers.graph.Coordinate2D;

public class PolarFunction2D extends PolynomialFunction2D {

    public PolarFunction2D(String function) {
	super(function);
    }
    
    @Override
    public Coordinate2D evaluateAt(double value) {
	double x = super.evaluateAt(value).getY() * Math.cos(value);
	double y = super.evaluateAt(value).getY() * Math.sin(value);
	return new Coordinate2D(x, y);
    }
    
    @Override
    public String getParameter() {
	return "h";
    }

}
