package edu.osu.cse.misc.graph.wrappers.vector;

import java.awt.Graphics2D;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.wrappers.graph.Graph2D;

/**
 * Simply represents multiple vectors to perform some calculations on.
 * Can be thought to have each Vector head-to-tail with each other, starting
 * from the origin.
 */
public class VectorChain {

	private LinkedList<Vector> vectors = new LinkedList<>();

	/**
	 * Add a Vector to the chain.
	 * 
	 * @param v A Vector
	 */
	public void add(Vector v) {
		this.vectors.add(v);
	}

	/**
	 * The resulting vector of summing each Vector in this chain.
	 * 
	 * @return A Vector representing the resultant of each Vector in this VectorChain.
	 */
	public Vector getResultant() {
		return getResultant(this.vectors.size());
	}

	/**
	 * Calculates the resultant of the Vectors in this chain up to a given index.
	 * 
	 * @param upTo The index in which to calculate up to.
	 * @return A Vector representing the sum of each looked-at Vector's components.
	 */
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

	/**
	 * Draws this VectorChain to the provided Graph.
	 * 
	 * @param g A Graphics object to draw to.
	 * @param graph A Graph that will be drawn on.
	 */
	public void drawAll(Graphics2D g, Graph2D graph) {
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
