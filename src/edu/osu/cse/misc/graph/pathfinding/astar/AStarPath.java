package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class AStarPath extends Path {

	public LinkedHashSet<Node> open = new LinkedHashSet<>();
	public LinkedHashSet<Node> closed = new LinkedHashSet<>();
	private Node start, finish;

	public AStarPath(Node start, Node finish) {
		this.start = start;
		this.finish = finish;
	}

	/**
	 * The start point of this Path.
	 * 
	 * @return A Node representing the start of this Path
	 */
	public Node getStart() {
		return this.start;
	}

	/**
	 * The end point of this Path.
	 * 
	 * @return A Node representing the end of this Path
	 */
	public Node getFinish() {
		return this.finish;
	}

	@Override
	public Node[] toNodeArray(boolean useDiagonals) {
		Node current = this.getStart();
		current.aStarProperties.setHeuristic();
		current.aStarProperties.open(open, closed);
		while (!this.open.isEmpty()) {
			System.out.println("Iterating");
			current = findLowestTotalCost(this.open);
			if (current.equals(this.finish)) {
				return this.getPath();
			}
			current.aStarProperties.close(open, closed);
			for (Node neighbor : current.getNeighbors(useDiagonals)) {
				System.out.println("Visiting neighbor");
				if (neighbor.type != NodeType.BLOCKED) {
					if (!this.closed.contains(neighbor)) {
						if (!this.open.contains(neighbor)) {
							neighbor.aStarProperties.open(open, closed);
							neighbor.aStarProperties.parent = current;
							neighbor.aStarProperties.setValues();
						}
						else {
							Node theoretical = new Node(neighbor.grid, neighbor.x, neighbor.y); //Imaginary node parented to the current to calculate its movement cost.
							theoretical.aStarProperties.parent = current;
							theoretical.aStarProperties.setMovementCost();
							if (theoretical.aStarProperties.movementCost < neighbor.aStarProperties.movementCost) {
								neighbor.aStarProperties.parent = current;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private Node[] getPath() {
		if (this.getFinish().aStarProperties.parent == null) {
			System.err.println("End has no parent. Path is not generated yet.");
			return new Node[] {  };
		}
		LinkedList<Node> nodes = new LinkedList<>();
		Node current = this.getFinish();
		do {
			System.out.println("Tracing back");
			nodes.add(current);
			current = current.aStarProperties.parent;
		} while (current != this.getStart());
		return nodes.toArray(new Node[nodes.size()]);
	}

	private Node findLowestTotalCost(Collection<Node> nodes) {
		Node lowest = null;
		for (Node node: nodes) {
			if (lowest == null || node.aStarProperties.totalCost < lowest.aStarProperties.totalCost) {
				lowest = node;
			}
		}
		return lowest;
	}

}
