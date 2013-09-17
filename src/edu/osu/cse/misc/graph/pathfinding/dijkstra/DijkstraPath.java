package edu.osu.cse.misc.graph.pathfinding.dijkstra;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.pathfinding.astar.AStarPath;
import edu.osu.cse.misc.graph.pathfinding.impl.gridsearch.components.GridPanel;
import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class DijkstraPath extends Path {

	private LinkedHashSet<Node> visited = new LinkedHashSet<>();
	private LinkedHashSet<Node> checkpoints = new LinkedHashSet<>();
	private Node start, finish;
	private GridPanel gridPanel;
	private Node[] nodes;

	public DijkstraPath(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		this.start = gridPanel.grid.start;
		this.finish = gridPanel.grid.finish;
	}

	public DijkstraPath(Node start, Node finish, GridPanel gridPanel) {
		System.out.println("gridPanel: " + gridPanel);
		this.gridPanel = gridPanel;
		this.start = gridPanel.grid.start = start;
		this.finish = gridPanel.grid.finish = finish;
	}

	public void addCheckpoint(Node checkpoint) {
		this.checkpoints.add(checkpoint);
	}

	@Override
	public Node[] toNodeArray(boolean useDiagonal) {
		if (nodes != null) {
			System.out.println("Re-using stored nodes");
			return nodes;
		}

		if (this.checkpoints.size() > 0) {
			System.out.println("Handling checkpoints");
			LinkedList<Node> allNodes = new LinkedList<>();

			Node nextStart = this.start;
			Node originalFinish = this.finish;
			while (this.checkpoints.size() > 0) {
				DijkstraPath nearestCheckpoint = null; //A Path from the previous start to the next checkpoint
				for (Node checkpoint : this.checkpoints) {
					DijkstraPath pathToCheckpoint = new DijkstraPath(nextStart, checkpoint, this.gridPanel);
					DijkstraProperties.registerProperties(this.gridPanel.grid, pathToCheckpoint);
					if (nearestCheckpoint == null || pathToCheckpoint.toNodeArray(useDiagonal).length < nearestCheckpoint.toNodeArray(useDiagonal).length) {
						nearestCheckpoint = pathToCheckpoint;
					}
				}
				nextStart = nearestCheckpoint.finish;
				allNodes.addAll(Arrays.asList(nearestCheckpoint.toNodeArray(useDiagonal)));
				this.checkpoints.remove(nearestCheckpoint.finish);
			}
			allNodes.addAll(Arrays.asList(new AStarPath(nextStart, originalFinish, this.gridPanel).toNodeArray(useDiagonal)));
			return allNodes.toArray(new Node[allNodes.size()]);
		}
		else {
			System.out.println("Finding path");
			Node current = this.start;
			current.dijkstraProperties(this).temporaryValue = 0;
			visited.add(current);
			while (this.finish.dijkstraProperties(this).permanentValue < 0) {
				Node lowest = getLowestTempValue(this.visited);
				if (lowest == null) {
					System.err.println("We ran out of open Nodes.");
					return null;
				}
				lowest.dijkstraProperties(this).permanentValue = lowest.dijkstraProperties(this).temporaryValue;
				current = lowest;
				for (Node neighbor : current.getNeighbors(useDiagonal)) {
					if (neighbor.type != NodeType.BLOCKED) {
						if (!visited.contains(neighbor)) {
							if (neighbor.dijkstraProperties(this).permanentValue < 0) {
								int movementCostToNeighbor = current.dijkstraProperties(this).getMovementCostTo(neighbor);
								if (neighbor.dijkstraProperties(this).temporaryValue < 0 || movementCostToNeighbor < neighbor.dijkstraProperties(this).temporaryValue) {
									neighbor.dijkstraProperties(this).temporaryValue = movementCostToNeighbor;
								}
								visited.add(neighbor);
							}
						}
					}
				}
			}
		}
		return (nodes = traceBack(useDiagonal));
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

	private Node[] traceBack(boolean useDiagonals) {
		LinkedList<Node> nodes = new LinkedList<>();
		Node current = this.finish;
		do {
			for (Node neighbor : current.getNeighbors(useDiagonals)) {
				if (neighbor.type != NodeType.BLOCKED) {
					int remainder = current.dijkstraProperties(this).permanentValue - current.dijkstraProperties(this).getRawMovementCostTo(neighbor);
					if (remainder == neighbor.dijkstraProperties(this).permanentValue) {
						current = neighbor;
						if (neighbor != this.start)
							nodes.add(current);
					}
				}
			}
		} while(current != this.start);
		return nodes.toArray(new Node[nodes.size()]);
	}


}
