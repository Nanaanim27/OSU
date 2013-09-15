package edu.osu.cse.misc.graph.plotting.wrappers.function._2d;

import edu.osu.cse.misc.graph.plotting.wrappers.graph.Coordinate2D;

public class PolarFunction2D extends PolynomialFunction2D {

	private AngleType type = AngleType.DEGREES;

	public PolarFunction2D(String function) {
		super(function);
	}

	@Override
	public Coordinate2D evaluateAt(double value) {
		if (this.type != AngleType.RADIANS) {
			value = Math.toRadians(value);
		}
		double x = super.evaluateAt(value).getY() * Math.cos(value);
		double y = super.evaluateAt(value).getY() * Math.sin(value);
		return new Coordinate2D(x, y);
	}
	
	@Override
	public Coordinate2D[] evaluateFrom(double valueMin, double valueMax, double accuracy) {
		return super.evaluateFrom(0D, 360D, accuracy);
	}

	@Override
	public String getParameter() {
		return "h";
	}

	public void setAngleType(AngleType type) {
		this.type = type;
	}

	public enum AngleType {
		DEGREES,
		RADIANS
	}
	
	@Override
	public PolarFunction2D restrict(double valueMin, double valueMax) {
		return (PolarFunction2D) super.restrict(valueMin, valueMax);
	}

}
