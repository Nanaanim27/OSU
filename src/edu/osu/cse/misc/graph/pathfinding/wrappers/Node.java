package edu.osu.cse.misc.graph.pathfinding.wrappers;

import java.util.ArrayList;

/**
 * A Node is represents an immutable x/y position, and a flag which denotes its state.
 */
public class Node {

	private final int x, y;
	private NodeType type = NodeType.UNCHECKED;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, NodeType type) {
		this(x, y);
		this.type = type;
	}
	
	/**
	 * Creates a new Node object based off of this Node's position, and translates it respectively.
	 * <br />Uses Swing's x/y directions, meaning +1 y is to the "north".
	 * 
	 * @param dX Difference in the x position to translate
	 * @param dY Difference in the y position to translate
	 * @return A new Node at the translated position
	 */
	public Node translate(int dX, int dY) { 
		return new Node(this.x + dX, this.y + dY);
	}
	
	/**
	 * Gathers all of the neighbors of this Node.
	 * <br />They are added to the returned list if and only if they are not {@link #isBlocked() blocked}, and are {@link #isOpen() open}.
	 * 
	 * @param includeDiagonals Whether to include diagonal Nodes or not.
	 * @return An array of Node objects.
	 */
	public Node[] getNeighbors(boolean includeDiagonals) {
		ArrayList<Node> nodes = new ArrayList<>(8);
		for (Node side : new Node[] { this.translate(0, 1), this.translate(0, -1), this.translate(-1, 0), this.translate(1, 0) }) {
			if (!side.isBlocked() && side.isOpen()) {
				nodes.add(side);
			}
		}
		
		if (includeDiagonals) {
			for (Node diag : new Node[] { this.translate(1, 1), this.translate(-1, -1), this.translate(-1, 1), this.translate(1, -1) }) {
				if (!diag.isBlocked() && diag.isOpen()) {
					nodes.add(diag);
				}
			}
		}
		
		return nodes.toArray(new Node[nodes.size()]);
	}
	
	/**
	 * Sets the type of this Node.
	 * 
	 * @param type The NodeType to set to
	 */
	public void setType(NodeType type) {
		this.type = type;
	}
	
	/**
	 * Checks whether the node represents a wall, thus being impassable.
	 * 
	 * @return <tt>true</tt> if this Node is a wall. <tt>false</tt> otherwise.
	 */
	public boolean isBlocked() {
		return this.type != NodeType.BLOCKED;
	}
	
	/**
	 * Checks whether the node is open for evaluation
	 * 
	 * @return <tt>true</tt> if this Node is a wall. <tt>false</tt> otherwise.
	 */
	public boolean isOpen() {
		return !this.isBlocked() && this.type != NodeType.CHECKED;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node other = (Node) obj;
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}
	
	
	
}
