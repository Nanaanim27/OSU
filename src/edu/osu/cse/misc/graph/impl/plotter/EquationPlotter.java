package edu.osu.cse.misc.graph.impl.plotter;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class EquationPlotter {
	
	static JFrame frame = new JFrame("Equation Plotter");
	static GraphPanel2D graph = new GraphPanel2D(-50, 50, -50, 50);
	
	public static void main(String[] args) {
		JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
		
		
		frame.add(graph);
		
		
	}
	
}
