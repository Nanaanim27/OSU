package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.LinkedHashSet;

import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * Represents the various properties necessary to compute an A* Path
 */
public class AStarProperties {

	public Node owner, parent, start, finish;
	public int heuristic, movementCost, totalCost;

	public AStarProperties(Node owner, Node start, Node finish) {
		this.owner = owner;
		this.start = start;
		this.finish = finish;
	}

	public void setValues() {
		this.setHeuristic();
		this.setMovementCost();
		this.setTotalCost();
	}

	public void setHeuristic() {
		this.heuristic = Math.abs(this.finish.x - this.owner.x) + Math.abs(this.finish.y - this.owner.x);
	}

	public void setMovementCost() {
		int cost = 0;
		Node current = owner;
		Node parent;
		while (!current.equals(this.start)) {
			parent = current.aStarProperties.parent;
			if (parent != null) {
				cost += (int) 10 * (Math.hypot(Math.abs(parent.x - current.x), Math.abs(parent.y - current.y)));
				current = current.aStarProperties.parent;
			}
		}
		this.movementCost = cost;
	}

	public void setTotalCost() {
		this.totalCost = this.heuristic + this.movementCost;
	}

	public void open(LinkedHashSet<Node> open, LinkedHashSet<Node> closed) {
		open.add(owner);
		closed.remove(owner);
	}

	public void close(LinkedHashSet<Node> open, LinkedHashSet<Node> closed) {
		closed.add(owner);
		open.remove(owner);
	}

	@Override
	public String toString() {
		return "Owner: " + owner + ", start: " + start + ", finish: " + finish;
	}

}
