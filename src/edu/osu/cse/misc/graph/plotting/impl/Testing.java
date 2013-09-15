package edu.osu.cse.misc.graph.plotting.impl;

import javax.swing.JFrame;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.PolarFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph.GraphPanel2D;





public class Testing {

	public static void main(String[] args) {
		testGraph(
				new PolarFunction2D("2theta")
				);
	}

	private static void testGraph(AbstractFunction2D...functions) {
		JFrame frame = new JFrame();

		GraphPanel2D graph = new GraphPanel2D(-10, 10, -10, 10);
		
		for (AbstractFunction2D function : functions) {
			graph.addFunction(function);
		}

		frame.add(graph);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static void testPlotter() {
		JFrame frame = new JFrame();
		
		
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
