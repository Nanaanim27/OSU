package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.impl.combinedplotter.util.Plot2DUtils;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

public class Plot2D extends Canvas {
	
	public final float xMin, xMax, yMin, yMax;
	private final float xMinReal, xMaxReal, yMinReal, yMaxReal;
	
	public final float xInterval, yInterval;
	
	private LinkedList<AbstractFunction2D> functions = new LinkedList<AbstractFunction2D>();
	
	public Plot2D(float xMin, float xMax, float yMin, float yMax, float xInterval, float yInterval) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
		this.xInterval = xInterval;
		this.yInterval = yInterval;

		this.xMinReal = Plot2DUtils.getRealValue(xMin, xInterval);
		this.xMaxReal = Plot2DUtils.getRealValue(xMax, xInterval);
		this.yMinReal = Plot2DUtils.getRealValue(yMin, yInterval);
		this.yMaxReal = Plot2DUtils.getRealValue(yMax, yInterval);
		
		this.addFunction(new PolynomialFunction2D("x^3"));
	}
	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		
		drawGrid(g);
		drawFunctions(g);
	}
	
	private void drawGrid(Graphics2D g) {
		for (float x = this.xMinReal; x <= this.xMaxReal; x += this.xInterval) {
			Point top = Plot2DUtils.getScreenPoint(new Coordinate2D(x, this.yMaxReal), this);
			Point bot = Plot2DUtils.getScreenPoint(new Coordinate2D(x, this.yMinReal), this);
			System.out.println("top: " + top + ", bot: " + bot);
			if (Float.floatToIntBits(x) == 0)
				g.setStroke(new BasicStroke(3f));
			else
				g.setStroke(new BasicStroke(1f));
			g.drawLine(top.x, top.y, bot.x, bot.y);
		}
		for (float y = this.yMinReal; y <= this.yMaxReal; y += this.yInterval) {
			Point left = Plot2DUtils.getScreenPoint(new Coordinate2D(this.xMinReal, y), this);
			Point right = Plot2DUtils.getScreenPoint(new Coordinate2D(this.xMaxReal, y), this);
			if (Float.floatToIntBits(y) == 0)
				g.setStroke(new BasicStroke(3f));
			else
				g.setStroke(new BasicStroke(1f));
			g.drawLine(left.x, left.y, right.x, right.y);
		}
	}
	
	private void drawFunctions(Graphics2D g) {
		g.setColor(Color.red);
		for (AbstractFunction2D f : this.functions) {
			Coordinate2D[] coords = f.evaluateFrom(this.xMin, this.xMax, 0.05f);
			for (int i = 0; i < coords.length-1; i++) {
				Point left = Plot2DUtils.getScreenPoint(coords[i], this);
				Point right = Plot2DUtils.getScreenPoint(coords[i+1], this);
				g.drawLine(left.x, left.y, right.x, right.y);
			}
		}
	}
	
	//The coordinate-length of the x-axis
	public float getXAxisLength() {
		return this.xMax - this.xMin;
	}
	
	//The coordinate-length of the y-axis
	public float getYAxisLength() {
		return this.yMax - this.yMin;
	}
	
	public boolean addFunction(AbstractFunction2D function) {
		if (this.functions.add(function)) {
			this.repaint();
			return true;
		}
		return false;
	}
	
	public boolean removeFunction(AbstractFunction2D function) {
		if (this.functions.remove(function)) {
			this.repaint();
			return true;
		}
		return false;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
}