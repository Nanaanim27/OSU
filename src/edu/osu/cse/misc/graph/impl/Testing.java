package edu.osu.cse.misc.graph.impl;

import javax.swing.JFrame;

import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.wrappers.function._2d.ParametricFunction2D;
import edu.osu.cse.misc.graph.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.Graph2D;





public class Testing {

	public static void main(String[] args) {
		testGraph(new ParametricFunction2D(new PolynomialFunction2D("5*cos(2t)"), new PolynomialFunction2D("5*sin(2t)")));
	}

	private static void testGraph(AbstractFunction2D...functions) {
		JFrame frame = new JFrame();

		Graph2D graph = new Graph2D(-10, 10, -10, 10);

		for (AbstractFunction2D function : functions) {
			graph.addFunction(function);
		}

		frame.add(graph);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}