package edu.osu.cse.misc.graph.pathfinding.dijkstra;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class DijkstraPath extends Path {

	private LinkedHashSet<Node> visited = new LinkedHashSet<>();
	private LinkedHashSet<Node> checkpoints = new LinkedHashSet<>();

	public DijkstraPath(Node start, Node finish, Grid grid) {
		super(start, finish, grid);
	}

	public DijkstraPath(Grid grid) {
		super(grid);
	}

	public void addCheckpoint(Node checkpoint) {
		this.checkpoints.add(checkpoint);
	}

	@Override
	public NodeQuery toNodeQuery(boolean useDiagonal) {
		if (this.nodes != null) {
			return this.nodes;
		}

		if (this.checkpoints.size() > 0) {
			System.out.println("Handling checkpoints");
			LinkedList<Node> allNodes = new LinkedList<>();

			Node nextStart = this.start;
			Node originalFinish = this.finish;
			while (this.checkpoints.size() > 0) {
				DijkstraPath nearestCheckpoint = null; //A Path from the previous start to the next checkpoint
				for (Node checkpoint : this.checkpoints) {
					DijkstraPath pathToCheckpoint = new DijkstraPath(nextStart, checkpoint, this.grid);
					DijkstraProperties.registerProperties(this.grid, pathToCheckpoint);
					if (nearestCheckpoint == null || pathToCheckpoint.toNodeQuery(useDiagonal).size() < nearestCheckpoint.toNodeQuery(useDiagonal).size()) {
						nearestCheckpoint = pathToCheckpoint;
					}
				}
				nextStart = nearestCheckpoint.finish;
				allNodes.addAll(nearestCheckpoint.toNodeQuery(useDiagonal));
				this.checkpoints.remove(nearestCheckpoint.finish);
			}
			DijkstraPath temp = new DijkstraPath(nextStart, originalFinish, this.grid);
			DijkstraProperties.registerProperties(this.grid, temp);
			allNodes.addAll(temp.toNodeQuery(useDiagonal));
			return new NodeQuery(allNodes.toArray(new Node[allNodes.size()]));
		}
		else {
			System.out.println("Finding path");
			Node current = this.start;
			current.dijkstraProperties(this).temporaryValue = 0;
			this.visited.add(current);
			while (this.finish.dijkstraProperties(this).permanentValue < 0) {
				Node lowest = getLowestTempValue(this.visited);
				if (lowest == null) {
					System.err.println("We ran out of open Nodes.");
					return null;
				}
				lowest.dijkstraProperties(this).permanentValue = lowest.dijkstraProperties(this).temporaryValue;
				current = lowest;
				for (Node neighbor : current.getNeighbors(useDiagonal).filter(NodeType.BLOCKED)) {
					if (!this.visited.contains(neighbor)) {
						if (neighbor.dijkstraProperties(this).permanentValue < 0) {
							int movementCostToNeighbor = current.dijkstraProperties(this).getMovementCostTo(neighbor);
							if (neighbor.dijkstraProperties(this).temporaryValue < 0 || movementCostToNeighbor < neighbor.dijkstraProperties(this).temporaryValue) {
								neighbor.dijkstraProperties(this).temporaryValue = movementCostToNeighbor;
							}
							this.visited.add(neighbor);
						}
					}
				}
			}
		}
		return (this.nodes = getPath(useDiagonal));
	}

	private Node getLowestTempValue(LinkedHashSet<Node> nodes) {
		Node lowestNode = null;
		for (Node node : nodes) {
			if (node.dijkstraProperties(this).permanentValue < 0) {
				if (lowestNode == null || node.dijkstraProperties(this).temporaryValue < lowestNode.dijkstraProperties(this).temporaryValue) {
					lowestNode = node;
				}
			}
		}
		return lowestNode;
	}

	/**
	 * Navigates backwards from the finish Node and adds each parent to a list of Nodes.
	 * <br />The resulting array will be sorted from start to finish.
	 * 
	 * @return An array of Nodes representing the path of this Path.
	 */
	private NodeQuery getPath(boolean useDiagonals) {
		LinkedList<Node> nodes = new LinkedList<>();
		Node current = this.finish;
		do {
			for (Node neighbor : current.getNeighbors(useDiagonals)) {
				if (neighbor.type != NodeType.BLOCKED) {
					int remainder = current.dijkstraProperties(this).permanentValue - current.dijkstraProperties(this).getRawMovementCostTo(neighbor);
					if (remainder == neighbor.dijkstraProperties(this).permanentValue) {
						current = neighbor;
						if (neighbor != this.start)
							nodes.addFirst(current);
					}
				}
			}
		} while(current != this.start);
		return new NodeQuery(nodes.toArray(new Node[nodes.size()]));
	}


}
