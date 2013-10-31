package edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d;

import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;

/**
 * Holds the form used to construct a given equation.
 * Only a single instance of each form is needed, so holding it
 * in an enum constant ensures that no extra forms are created.
 */
public enum Equation3D {
	
	POLYNOMIAL(new PolynomialEquationForm3D()),
	PARAMETRIC(new ParametricEquationForm3D());
	
	private AbstractEquationForm3D<? extends AbstractFunction3D> form;
	
	Equation3D(AbstractEquationForm3D<? extends AbstractFunction3D> form) {
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
	public AbstractEquationForm3D<? extends AbstractFunction3D> getForm() {
		return this.form;
	}
	
	/**
	 * Collects the {@link #getName()} for each Equation into an array.
	 * <br />Used to represent each type of Equation in a list of some sort.
	 * 
	 * @return An array of strings representing the name of each Equation.
	 */
	public static String[] getNames() {
		Equation3D[] allEquations = values();
		String[] names = new String[allEquations.length];
		for (int i = 0; i < names.length && i < allEquations.length; i++) {
			names[i] = allEquations[i].getName();
		}
		return names;
	}
}
