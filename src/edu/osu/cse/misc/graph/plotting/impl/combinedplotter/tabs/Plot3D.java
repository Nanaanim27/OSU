package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Deque;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;

/**
 * A canvas that supports the drawing of Three-Dimensional functions
 */
public class Plot3D extends Canvas {

	private Deque<AbstractFunction3D> functions = new LinkedList<>();
	private final Dimension SIZE = new Dimension(500, 500);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public boolean add(AbstractFunction3D function) {
		return this.functions.add(function);
	}
	
	public boolean remove(AbstractFunction3D function) {
		return this.functions.remove(function);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return this.SIZE;
	}
	
	
}
