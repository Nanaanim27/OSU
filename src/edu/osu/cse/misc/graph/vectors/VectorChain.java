package edu.osu.cse.misc.graph.vectors;

import java.awt.Graphics2D;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.GraphPanel2D;

/**
 * A VectorChain represents what is commonly taught as a "head-to-tail" visualization of multiple Vectors.
 */
public class VectorChain {

	private LinkedList<Vector> vectors = new LinkedList<>();

	public void add(Vector v) {
		this.vectors.add(v);
	}

	/**
	 * The resultant of this VectorChain.
	 * <br />Mathematically, simply the sum of each term of each Vector.
	 * 
	 * @return A new Vector representing the "tail" of the first vector and the "head" of the last.
	 */
	public Vector getResultant() {
		return getResultant(this.vectors.size());
	}

	private Vector getResultant(int upTo) {
		float x = 0, y = 0, z = 0;
		for (int i = 0; i < upTo; i++) {
			Vector v = this.vectors.get(i);
			x += v.x;
			y += v.y;
			z += v.z;
		}
		return new Vector(x, y, z);
	}

	/**
	 * Draws each Vector in this VectorChain to a given GraphPanel.
	 * <br />The resultant of the chain is excluded.
	 * 
	 * @param g A Graphics2D object to draw on.
	 * @param graph A GraphPanel that houses the Graphics2D to draw on.
	 */
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
