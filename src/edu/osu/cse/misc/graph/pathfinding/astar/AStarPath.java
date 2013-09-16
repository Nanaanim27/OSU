package edu.osu.cse.misc.graph.pathfinding.astar;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.pathfinding.impl.components.GridPanel;
import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class AStarPath extends Path {

	private LinkedHashSet<Node> open = new LinkedHashSet<>();
	private LinkedHashSet<Node> closed = new LinkedHashSet<>();
	private LinkedHashSet<Node> checkpoints = new LinkedHashSet<>();
	private Node start, finish;
	private GridPanel gridPanel;

	private Node[] nodes;

	public AStarPath(GridPanel gridPanel) {
		this.start = gridPanel.grid.start;
		this.finish = gridPanel.grid.finish;
		this.gridPanel = gridPanel;
	}
	
	public AStarPath(Node start, Node finish, GridPanel gridPanel) {
		this.start = gridPanel.grid.start = start;
		this.finish = gridPanel.grid.finish = finish;
		this.gridPanel = gridPanel;
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
	public Node[] toNodeArray(boolean useDiagonals) {
		if (nodes != null) {
			return nodes;
		}
		if (this.checkpoints.size() > 0) {
			LinkedList<Node> allNodes = new LinkedList<>();

			Node nextStart = this.start;
			Node originalFinish = this.finish;
			while (this.checkpoints.size() > 0) {
				AStarPath nearestCheckpoint = null; //A Path from the previous start to the next checkpoint
				for (Node checkpoint : this.checkpoints) {
					AStarPath pathToCheckpoint = new AStarPath(nextStart, checkpoint, this.gridPanel);
					if (nearestCheckpoint == null || pathToCheckpoint.toNodeArray(useDiagonals).length < nearestCheckpoint.toNodeArray(useDiagonals).length) {
						nearestCheckpoint = pathToCheckpoint;
					}
				}
				nextStart = nearestCheckpoint.finish;
				allNodes.addAll(Arrays.asList(nearestCheckpoint.toNodeArray(useDiagonals)));
				this.checkpoints.remove(nearestCheckpoint.finish);
			}
			allNodes.addAll(Arrays.asList(new AStarPath(nextStart, originalFinish, this.gridPanel).toNodeArray(useDiagonals)));
			return allNodes.toArray(new Node[allNodes.size()]);
		}
		else {
			Node current = this.getStart();
			current.aStarProperties.setHeuristic();
			current.aStarProperties.open(open, closed);
			while (!this.open.isEmpty()) {
				current = findLowestTotalCost(this.open);
				if (current.equals(this.finish)) {
					return (nodes = this.getPath());
				}
				current.aStarProperties.close(open, closed);
				Node[] neighbors = current.getNeighbors(useDiagonals);
				for (Node neighbor : neighbors) {
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
			if (current.type != NodeType.START && current.type != NodeType.FINISH && current.type != NodeType.CHECKPOINT) {
				nodes.add(current);
			}
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
