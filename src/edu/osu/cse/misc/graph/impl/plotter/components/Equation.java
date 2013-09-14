package edu.osu.cse.misc.graph.impl.plotter.components;

enum Equation {
	
	POLYNOMIAL,
	PARAMETRIC,
	POLAR;
	
	/**
	 * The name of this function. For example, a POLYNOMIAL would return as Polynomial
	 */
	public String getName() {
		String allLower = this.toString().toLowerCase();
		return allLower.substring(0, 1).toUpperCase() + allLower.substring(1);
	}
	
	public static String[] getNames() {
		Equation[] allEquations = values();
		String[] names = new String[allEquations.length];
		for (int i = 0; i < names.length && i < allEquations.length; i++) {
			names[i] = allEquations[i].getName();
		}
		return names;
	}
}
