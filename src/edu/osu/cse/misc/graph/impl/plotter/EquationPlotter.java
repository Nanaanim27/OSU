package edu.osu.cse.misc.graph.impl.plotter;

import javax.swing.JFrame;

import edu.osu.cse.misc.graph.impl.plotter.components.equationform.PolynomialEquationForm;
import edu.osu.cse.misc.graph.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class EquationPlotter {
	
	static JFrame frame = new JFrame("Equation Plotter");
	public static GraphPanel2D graph = new GraphPanel2D(-50, 50, -50, 50) {{
		setXInterval(5D);
		setYInterval(5D);
	}};
	
	public static void main(String[] args) {
		graph.addFunction(new PolynomialFunction2D("sin(x)"));
		frame.add(new PolynomialEquationForm(graph));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
