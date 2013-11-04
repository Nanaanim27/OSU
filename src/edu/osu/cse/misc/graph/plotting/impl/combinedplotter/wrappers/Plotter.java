package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.wrappers;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Deque;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;

public abstract class Plotter extends Canvas {
	
	private Deque<AbstractFunction2D> functions = new LinkedList<>();
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}
	
	public boolean add(AbstractFunction2D function) {
		return this.functions.add(function);
	}
	
	public boolean remove(AbstractFunction2D function) {
		return this.functions.remove(function);
	}
	
}
