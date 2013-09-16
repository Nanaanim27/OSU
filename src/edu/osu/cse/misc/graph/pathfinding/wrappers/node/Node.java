package edu.osu.cse.misc.graph.pathfinding.wrappers.node;

import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.osu.cse.misc.graph.pathfinding.astar.AStarProperties;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;


/**
 * A Node is represents an immutable x/y position, and a flag which denotes its state.
 */
public class Node {

	public static final int SIZE = 15;
	public final int x, y;
	public final Grid grid;
	public NodeType type;
	private int id;

	public AStarProperties aStarProperties;

	static int count = 0;
	
	public Node(Grid grid, int x, int y) {
		this.id = count++;
		this.x = x;
		this.y = y;
		this.grid = grid;
		this.type = NodeType.UNBLOCKED;
		this.aStarProperties = new AStarProperties(this, grid.start, grid.finish);
	}

	public Node translate(int dX, int dY) {
		return this.grid.getNode(this.x + dX, this.y + dY);
	}
	
	public int getId() {
		return this.id;
	}

	public Node[] getNeighbors(boolean includeDiagonals) {
		ArrayList<Node> nodes = new ArrayList<>(8);
		for (Node side : new Node[] { this.translate(0, 1), this.translate(0, -1), this.translate(-1, 0), this.translate(1, 0) }) {
			if (side != null) {
				nodes.add(side);
			}
		}

		if (includeDiagonals) {
			for (Node diag : new Node[] { this.translate(1, 1), this.translate(-1, -1), this.translate(-1, 1), this.translate(1, -1) }) {
				if (diag != null) {
					nodes.add(diag);
				}
			}
		}
		return nodes.toArray(new Node[nodes.size()]);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(this.type.getColor());
		g.fillRect(x * Node.SIZE, y * Node.SIZE, Node.SIZE, Node.SIZE);
	}



	/**
	 * Auto Generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Compares an arbitrary object to this Node object for equality.
	 * <br />Checks for equality among the x/y coordinates of each Node.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node other = (Node) obj;
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}



}
