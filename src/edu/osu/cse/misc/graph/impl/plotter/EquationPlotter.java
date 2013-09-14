package edu.osu.cse.misc.graph.impl.plotter;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.osu.cse.misc.graph.impl.plotter.components.EquationSelecter;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class EquationPlotter {
	
	static JFrame frame = new JFrame("Equation Plotter");
	public static GraphPanel2D graph = new GraphPanel2D(-50, 50, -50, 50) {{
		setXInterval(5D);
		setYInterval(5D);
	}};
	
	public static void main(String[] args) {
		frame.add(new EquationSelecter());
		
		frame.setPreferredSize(new Dimension(750, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
