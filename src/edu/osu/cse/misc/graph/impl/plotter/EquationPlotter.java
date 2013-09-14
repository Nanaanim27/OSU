package edu.osu.cse.misc.graph.impl.plotter;

import javax.swing.JFrame;

import edu.osu.cse.misc.graph.impl.plotter.components.equationform.PolynomialEquationForm2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class EquationPlotter {
	
	static JFrame frame = new JFrame("Equation Plotter");
	static GraphPanel2D graph = new GraphPanel2D(-50, 50, -50, 50);
	
	public static void main(String[] args) {
		frame.add(new PolynomialEquationForm2D());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
