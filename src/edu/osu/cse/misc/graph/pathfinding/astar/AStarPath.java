package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class AStarPath extends Path {

	private LinkedHashSet<Node> open = new LinkedHashSet<>();
	private LinkedHashSet<Node> closed = new LinkedHashSet<>();
	private LinkedHashSet<Node> checkpoints = new LinkedHashSet<>();

	public AStarPath(Node start, Node finish, Grid grid) {
		super(start, finish, grid);
	}

	public AStarPath(Grid grid) {
		super(grid);
	}

	public void addCheckpoint(Node node) {
		this.checkpoints.add(node);
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
	public NodeQuery toNodeQuery(boolean useDiagonals) {
		if (this.nodes != null) {
			return this.nodes;
		}
		if (this.checkpoints.size() > 0) {
			LinkedList<Node> allNodes = new LinkedList<>();

			Node nextStart = this.start;
			Node originalFinish = this.finish;
			while (this.checkpoints.size() > 0) {
				AStarPath nearestCheckpoint = null; //A Path from the previous start to the next checkpoint
				for (Node checkpoint : this.checkpoints) {
					AStarPath pathToCheckpoint = new AStarPath(nextStart, checkpoint, this.grid);
					if (nearestCheckpoint == null || pathToCheckpoint.toNodeQuery(useDiagonals).size() < nearestCheckpoint.toNodeQuery(useDiagonals).size()) {
						nearestCheckpoint = pathToCheckpoint;
					}
				}
				nextStart = nearestCheckpoint.finish;
				allNodes.addAll(nearestCheckpoint.toNodeQuery(useDiagonals));
				this.checkpoints.remove(nearestCheckpoint.finish);
			}
			allNodes.addAll(new AStarPath(nextStart, originalFinish, this.grid).toNodeQuery(useDiagonals));
			return new NodeQuery(new Node[allNodes.size()]);
		}
		else {
			Node current = this.getStart();
			current.aStarProperties.setHeuristic();
			current.aStarProperties.open(this.open, this.closed);
			while (!this.open.isEmpty()) {
				current = findLowestTotalCost(this.open);
				if (current.equals(this.finish)) {
					return (this.nodes = this.getPath());
				}
				current.aStarProperties.close(this.open, this.closed);
				NodeQuery neighbors = current.getNeighbors(useDiagonals).filter(NodeType.BLOCKED);
				for (Node neighbor : neighbors) {
					if (!this.closed.contains(neighbor)) {
						if (!this.open.contains(neighbor)) {
							neighbor.aStarProperties.open(this.open, this.closed);
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

	/**
	 * Navigates backwards from the finish Node and adds each parent to a list of Nodes.
	 * <br />The resulting array will be sorted from start to finish.
	 * 
	 * @return An array of Nodes representing this Path.
	 */
	private NodeQuery getPath() {
		if (this.getFinish().aStarProperties.parent == null) {
			System.err.println("End has no parent. Path is not generated yet.");
			return new NodeQuery(new Node[] {  });
		}
		LinkedList<Node> nodes = new LinkedList<>();
		Node current = this.getFinish();
		do {
			if (current.type != NodeType.START && current.type != NodeType.FINISH && current.type != NodeType.CHECKPOINT) {
				nodes.addFirst(current);
			}
			current = current.aStarProperties.parent;
		} while (current != this.getStart());
		return new NodeQuery(nodes.toArray(new Node[nodes.size()]));
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
