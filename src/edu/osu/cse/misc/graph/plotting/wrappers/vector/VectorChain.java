package edu.osu.cse.misc.graph.plotting.wrappers.vector;

import java.awt.Graphics2D;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.graph.GraphPanel2D;

public class VectorChain {

    private LinkedList<Vector> vectors = new LinkedList<>();

    public void add(Vector v) {
	this.vectors.add(v);
    }

    public Vector getResultant() {
	return getResultant(this.vectors.size());
    }

    private Vector getResultant(int upTo) {
	double x = 0, y = 0, z = 0;
	for (int i = 0; i < upTo; i++) {
	    Vector v = this.vectors.get(i);
	    x += v.x;
	    y += v.y;
	    z += v.z;
	}
	return new Vector(x, y, z);
    }

    public void drawAll(Graphics2D g, GraphPanel2D graph) {
	for (int i = 0; i < this.vectors.size(); i++) {
	    Vector current = this.vectors.get(i);
	    if (i == 0) {
		current.draw2D(g, graph);
	    }
	    else {
		Vector last = this.getResultant(i);
		current.draw2D(g, last, graph);
	    }
	}
    }


}
