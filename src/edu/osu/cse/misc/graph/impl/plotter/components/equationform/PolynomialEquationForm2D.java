package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import javax.swing.JLabel;

public class PolynomialEquationForm2D extends AbstractEquationForm2D {

	@Override
	protected JLabel getVariableLabel() {
		return new JLabel("Variable: x");
	}

	@Override
	protected EquationField[] getEquationFields() {
		return new EquationField[] {
				new EquationField("x")
		};
	}

}
