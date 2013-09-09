package edu.osu.cse.misc.graph.lang;


public class FormulaFormatException extends Exception {

	public FormulaFormatException(String formula) {
		super("Invalid formula format: " + formula);
	}
	
}
