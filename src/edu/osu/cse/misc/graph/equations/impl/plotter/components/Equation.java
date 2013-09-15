package edu.osu.cse.misc.graph.equations.impl.plotter.components;

import edu.osu.cse.misc.graph.equations.impl.plotter.components.equationform.AbstractEquationForm;
import edu.osu.cse.misc.graph.equations.impl.plotter.components.equationform.ParametricEquationForm;
import edu.osu.cse.misc.graph.equations.impl.plotter.components.equationform.PolynomialEquationForm;
import edu.osu.cse.misc.graph.equations.wrappers.function._2d.AbstractFunction2D;

/**
 * 
 */
public enum Equation {
	
	POLYNOMIAL(new PolynomialEquationForm()),
	PARAMETRIC(new ParametricEquationForm()),
	POLAR(null);
	
	private AbstractEquationForm<? extends AbstractFunction2D> form;
	
	Equation(AbstractEquationForm<? extends AbstractFunction2D> form) {
		this.form = form;
	}
	
	/**
	 * The name of this function. For example, a POLYNOMIAL would return as Polynomial
	 * 
	 * @return A String
	 */
	public String getName() {
		String allLower = this.toString().toLowerCase();
		return allLower.substring(0, 1).toUpperCase() + allLower.substring(1);
	}
	
	/**
	 * The form used to fill out an equation of this Equation type.
	 * 
	 * @return A subclass instance of AbstractEquationForm, parameterized appropriately.
	 */
	public AbstractEquationForm<? extends AbstractFunction2D> getForm() {
		return this.form;
	}
	
	/**
	 * Collects the {@link #getName()} for each Equation into an array.
	 * <br />Used to represent each type of Equation in a list of some sort.
	 * 
	 * @return An array of strings representing the name of each Equation.
	 */
	public static String[] getNames() {
		Equation[] allEquations = values();
		String[] names = new String[allEquations.length];
		for (int i = 0; i < names.length && i < allEquations.length; i++) {
			names[i] = allEquations[i].getName();
		}
		return names;
	}
}
