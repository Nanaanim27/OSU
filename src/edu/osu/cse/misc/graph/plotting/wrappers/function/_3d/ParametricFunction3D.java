package edu.osu.cse.misc.graph.plotting.wrappers.function._3d;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._3d.Coordinate3D;


public class ParametricFunction3D extends AbstractFunction3D {

	private PolynomialFunction2D xT, yT, zT;
	
	public ParametricFunction3D(String xT, String yT, String zT) {
		this(new PolynomialFunction2D(xT), new PolynomialFunction2D(yT), new PolynomialFunction2D(zT));
	}
	
	public ParametricFunction3D(PolynomialFunction2D xT, PolynomialFunction2D yT, PolynomialFunction2D zT) {
		this.xT = xT.overrideParameter("t");
		this.yT = yT.overrideParameter("t");
		this.zT = zT.overrideParameter("t");
	}
	
	@Override
	public Coordinate3D evaluateAt(float value1, float value2) {
		return null;
	}
	
	public Coordinate3D evaluateAt(float value) {
		float x = this.xT.evaluateAt(value).getY();
		float y = this.yT.evaluateAt(value).getY();
		float z = this.zT.evaluateAt(value).getY();
		return new Coordinate3D(x, y, z);
	}

	@Override
	public String getParameter1() {
		return "t";
	}

	@Override
	public String getParameter2() {
		return "t";
	}
	
	@Override
	public boolean ensureValidity() {
		return this.xT.ensureValidity() && this.yT.ensureValidity() && this.zT.ensureValidity();
	}
	
	@Override
	public ParametricFunction3D restrict(float value1Min, float value1Max, float value2Min, float value2Max) {
		return (ParametricFunction3D) super.restrict(value1Min, value1Max, value2Min, value2Max);
	}
	
}
