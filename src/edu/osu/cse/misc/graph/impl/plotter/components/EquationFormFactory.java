package edu.osu.cse.misc.graph.impl.plotter.components;

import edu.osu.cse.misc.graph.impl.plotter.components.equationform.AbstractEquationForm;
import edu.osu.cse.misc.graph.impl.plotter.components.equationform.PolynomialEquationForm;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class EquationFormFactory {

	public static AbstractEquationForm createEquationForm(Equation eq) {
		switch(eq) {
		case POLYNOMIAL:
			return new PolynomialEquationForm(new GraphPanel2D(-20, 20, -20, 20));
		case PARAMETRIC:

		case POLAR:

		default:
			return null;
		}
	}

}
