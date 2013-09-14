package edu.osu.cse.misc.graph.impl.plotter.components;

import edu.osu.cse.misc.graph.impl.plotter.components.equationform.AbstractEquationForm2D;
import edu.osu.cse.misc.graph.impl.plotter.components.equationform.PolynomialEquationForm2D;

public class EquationFormFactory {

	public static AbstractEquationForm2D createEquationForm(Equation eq) {
		switch(eq) {
		case POLYNOMIAL:
			return new PolynomialEquationForm2D();
		case PARAMETRIC:

		case POLAR:

		default:
			return null;
		}
	}

}
