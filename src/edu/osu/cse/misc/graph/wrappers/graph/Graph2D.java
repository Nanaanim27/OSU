package edu.osu.cse.misc.graph.wrappers.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.wrappers.vector.Vector;
import edu.osu.cse.misc.graph.wrappers.vector.VectorChain;

public final class Graph2D extends JPanel {

    private static final Color[] COLORS = {
	Color.red, Color.blue, Color.green, Color.cyan, Color.magenta,
	Color.pink, Color.pink, Color.orange
    };

    private double xMin, xMax;
    private double yMin, yMax;

    private Dimension size;

    private LinkedList<AbstractFunction2D> functions = new LinkedList<>();

    public Graph2D(double xMin, double xMax, double yMin, double yMax) {
	this.xMin = xMin;
	this.xMax = xMax;
	this.yMin = yMin;
	this.yMax = yMax;
    }

    @Override
    public Dimension getPreferredSize() {
	if (this.size == null) {
	    this.size = new Dimension(500, 500);
	}
	return this.size;
    }

    public Point getCenterPoint() {
	return new Point((int) (this.getPreferredSize().width / 2D), (int) (this.getPreferredSize().height / 2D));
    }

    public Point convertToScreen(Coordinate2D coord) {
	return convertToScreen(coord.getX(), coord.getY());
    }

    public Point convertToScreen(double x, double y) {
	y = -y; //coord system y-direction is flipped from swing's
	Point center = this.getCenterPoint();
	return new Point((int) (center.x + (x * this.getXInterval())), (int) (center.y + (y * this.getYInterval())));
    }

    public double getXLength() {
	return Math.abs(this.xMin) + Math.abs(this.xMax);
    }

    public double getYLength() {
	return Math.abs(this.yMin) + Math.abs(this.yMax);
    }

    public double getXInterval() {
	return this.getPreferredSize().width / this.getXLength();
    }

    public double getYInterval() {
	return this.getPreferredSize().height / this.getYLength();
    }

    public boolean contains(Coordinate2D coord) {
	return (coord.getX() >= this.xMin && coord.getX() <= this.xMax) && (coord.getY() >= this.yMin && coord.getY() <= this.yMax);
    }

    @Override
    protected void paintComponent(Graphics g1) {
	super.paintComponent(g1);
	Graphics2D g = (Graphics2D) g1;

	drawGrid(g);
	drawFunctions(g);
    }

    private void drawGrid(Graphics2D g) {
	for (double y = 0; y <= this.getPreferredSize().height; y += this.getYInterval()) {
	    g.drawLine(0, (int) y, this.getPreferredSize().width, (int) y);
	}
	for (double x = 0; x <= this.getPreferredSize().width; x += this.getXInterval()) {
	    g.drawLine((int) x, 0, (int) x, this.getPreferredSize().height);
	}
	Point center = this.getCenterPoint();
	g.setStroke(new BasicStroke(3));
	g.drawLine(0, center.y, this.getPreferredSize().width, center.y);
	g.drawLine(center.x, 0, center.x, this.getPreferredSize().height);
	g.setStroke(new BasicStroke(1.5f));
    }

    private void drawFunctions(Graphics2D g) {
	for (int i = 0; i < this.functions.size(); i++) {
	    //g.setColor(COLORS[i % COLORS.length]);
	    AbstractFunction2D function = this.functions.get(i);
	    if (function.isValid()) {
		Coordinate2D[] points = function.evaluateFrom(0, 360, 0.05D);
		for (int p = 0; p < points.length; p++) {
		    if (p < points.length - 1) {
			Point cur = this.convertToScreen(points[p]);
			Point next = this.convertToScreen(points[p+1]);
			if (this.contains(cur))
			    g.drawLine((int) cur.getX(), (int) cur.getY(), (int) next.getX(), (int) next.getY());
		    }
		}
	    }
	    else {
		this.functions.remove(function);
	    }
	}
    }

    public void addFunction(AbstractFunction2D function) {
	this.functions.add(function);
    }

    public void removeFunction(AbstractFunction2D function) {
	this.functions.remove(function);
    }

}
