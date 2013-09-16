package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.LinkedHashSet;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * Represents the various properties necessary to compute an A* Path
 */
public class AStarProperties {

	public Grid grid;
	public Node owner, parent;
	public int heuristic, movementCost, totalCost;

	public AStarProperties(Node owner, Grid grid) {
		this.grid = grid;
		this.owner = owner;
	}

	public void setValues() {
		this.setHeuristic();
		this.setMovementCost();
		this.setTotalCost();
	}

	public void setHeuristic() {
		this.heuristic = Math.abs(this.grid.finish.x - this.owner.x) + Math.abs(this.grid.finish.y - this.owner.x);
	}

	public void setMovementCost() {
		int cost = 0;
		Node current = owner;
		Node parent;
		while (!current.equals(this.grid.start)) {
			parent = current.aStarProperties.parent;
			if (parent != null) {
				cost += (int) (10 * (Math.hypot(Math.abs(parent.x - current.x), Math.abs(parent.y - current.y))));
				current = parent;
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
		return "Owner: " + owner + ", start: " + this.grid.start + ", finish: " + this.grid.finish;
	}

}
