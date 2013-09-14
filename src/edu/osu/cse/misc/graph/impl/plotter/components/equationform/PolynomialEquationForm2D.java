package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import javax.swing.JLabel;

public class PolynomialEquationForm2D extends AbstractEquationForm2D {

	public PolynomialEquationForm2D() {
		this.build();
	}
	
	private EquationField2D field = new EquationField2D("x") {{
		setListener(this.submitEquation(graphPanel));
	}};
	
	protected String getVariable() {
		return "x";
	};

	@Override
	protected EquationField2D[] getEquationFields() {
		return new EquationField2D[] { this.field };
	}

}
