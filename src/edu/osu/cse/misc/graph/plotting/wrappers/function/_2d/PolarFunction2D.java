package edu.osu.cse.misc.graph.plotting.wrappers.function._2d;

import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

public class PolarFunction2D extends PolynomialFunction2D {

	private AngleType type = AngleType.DEGREES;

	public PolarFunction2D(String function) {
		super(function);
	}

	@Override
	public Coordinate2D evaluateAt(float value) {
		if (this.type != AngleType.RADIANS) {
			value = (float) Math.toRadians(value);
		}
		float x = super.evaluateAt(value).getY() * (float) Math.cos(value);
		float y = super.evaluateAt(value).getY() * (float) Math.sin(value);
		return new Coordinate2D(x, y);
	}
	
	@Override
	public Coordinate2D[] evaluateFrom(float valueMin, float valueMax, float accuracy) {
		return super.evaluateFrom(0f, 360f, accuracy);
	}

	@Override
	public String getParameter() {
		return "theta";
	}

	public void setAngleType(AngleType type) {
		this.type = type;
	}

	private enum AngleType {
		DEGREES,
		RADIANS
	}
	
	@Override
	public PolarFunction2D restrict(float valueMin, float valueMax) {
		return (PolarFunction2D) super.restrict(valueMin, valueMax);
	}

}
