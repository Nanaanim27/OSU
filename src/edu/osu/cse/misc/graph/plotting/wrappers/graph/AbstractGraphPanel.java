package edu.osu.cse.misc.graph.plotting.wrappers.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class AbstractGraphPanel extends JPanel {

	protected static final Color[] COLORS = {
		Color.red, Color.blue, Color.green, Color.cyan, Color.magenta,
		Color.pink, Color.pink, Color.orange
	};

	private Dimension size;
	
	@Override
	public Dimension getPreferredSize() {
		if (this.size == null) {
			this.size = new Dimension(500, 500);
		}
		return this.size;
	}
	
	/**
	 * The center point relative to the panel holding this Graph
	 * 
	 * @return A Point marking the center point.
	 */
	public Point getCenterPoint() {
		return new Point((int) (this.getPreferredSize().width / 2D), (int) (this.getPreferredSize().height / 2D));
	}
	
}
