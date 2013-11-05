package edu.osu.cse.misc.graph.plotting.impl.combinedplotter;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs.Plot2D;

public class CombinedPlotter {

	private JFrame frame = new JFrame("Combined Plotter");
	private JPanel container = new JPanel(new BorderLayout(3, 3));
	
	private CombinedPlotter() {
		
		this.container.add(new Plot2D(-10, 10, -10, 10, 1f, 1f));
		
		this.frame.add(this.container);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CombinedPlotter();
	}
}
