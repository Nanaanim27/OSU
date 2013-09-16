package edu.osu.cse.misc.graph.pathfinding.impl;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.pathfinding.impl.components.Controller;
import edu.osu.cse.misc.graph.pathfinding.impl.components.GridPanel;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;

public class PathFinder extends JFrame {

	public PathFinder() {
		JPanel container = new JPanel();
		final Grid grid = new Grid(50, 50);

		GridPanel gridPanel = new GridPanel(grid);
		container.add(gridPanel, BorderLayout.CENTER);
		container.add(new Controller(gridPanel), BorderLayout.WEST);
		def(container);
	}

	/** Default JFrame operations */
	private void def(JPanel container) {
		this.setTitle("Pathfinder");
		this.add(container);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new PathFinder();
	}
}
