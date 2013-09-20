package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.LinkedHashSet;

import edu.osu.cse.misc.graph.pathfinding.wrappers.NodeProperties;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * Represents the various properties necessary to compute an A* Path
 */
public class AStarProperties extends NodeProperties {

	public Node parent;
	public int heuristic, movementCost, totalCost;

	public AStarProperties(Node owner, Grid grid) {
		super(owner, grid);
	}

	/**
	 * Sets the heuristic, movementCost, and totalCost values respectively.
	 */
	public void setValues() {
		this.setHeuristic();
		this.setMovementCost();
		this.setTotalCost();
	}

	/**
	 * Sets the heuristic cost.
	 */
	public void setHeuristic() {
		this.heuristic = Math.abs(this.grid.finish.x - this.owner.x) + Math.abs(this.grid.finish.y - this.owner.x);
	}

	/**
	 * Sets the movement cost for the Node represented by this set of properties.
	 * <br />The movement cost is the total movement cost from the start Node to <tt>this</tt> Node.
	 */
	public void setMovementCost() {
		int cost = 0;
		Node current = this.owner;
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

	/**
	 * Sets the total cost of this Node.
	 * <br />Mathematically, the totalCost is simple the sum of the heuristic and movementCost.
	 */
	public void setTotalCost() {
		this.totalCost = this.heuristic + this.movementCost;
	}

	/**
	 * Adds the owner of this set of properties to the open list.
	 * Removes the owner of this set of properties to the closed list.
	 * 
	 * @param open The list of Nodes that are open for evaluation.
	 * @param closed The list of Nodes that have already been evaluated.
	 */
	public void open(LinkedHashSet<Node> open, LinkedHashSet<Node> closed) {
		open.add(this.owner);
		closed.remove(this.owner);
	}
	
	/**
	 * Adds the owner of this set of properties to the closed list.
	 * Removes the owner of this set of properties to the open list.
	 * 
	 * @param open The list of Nodes that are open for evaluation.
	 * @param closed The list of Nodes that have already been evaluated.
	 */
	public void close(LinkedHashSet<Node> open, LinkedHashSet<Node> closed) {
		closed.add(this.owner);
		open.remove(this.owner);
	}

	@Override
	public String toString() {
		return "Owner: " + this.owner + ", start: " + this.grid.start + ", finish: " + this.grid.finish;
	}

}
