package edu.osu.cse.misc.graph.pathfinding.dijkstra;

import java.util.HashMap;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

public class DijkstraProperties {

	private static HashMap<DijkstraPath, HashMap<Node, DijkstraProperties>> instanceMap = new HashMap<>();
	
	public Node owner;
	public Grid grid;
	public int temporaryValue = -1, permanentValue = -1;
	
	public DijkstraProperties(Node owner, Grid grid) {
		this.owner = owner;
		this.grid = grid;
	}
	
	public static DijkstraProperties getInstanceForPath(DijkstraPath pathInstance, Node owner) {
		return instanceMap.get(pathInstance).get(owner);
	}
	
	public static void registerProperties(Node owner, Grid grid, DijkstraPath pathInstance) {
		if (instanceMap.get(pathInstance) == null) {
			instanceMap.put(pathInstance, new HashMap<Node, DijkstraProperties>());
		}
		instanceMap.get(pathInstance).put(owner, new DijkstraProperties(owner, grid));
	}
	
	public static void registerProperties(Grid grid, DijkstraPath pathInstance) {
		if (instanceMap.get(pathInstance) == null) {
			instanceMap.put(pathInstance, new HashMap<Node, DijkstraProperties>());
		}
		for (Node n : grid.getNodes()) {
			instanceMap.get(pathInstance).put(n, new DijkstraProperties(n, grid));
		}
	}
	
	/**
	 * Calculates the cost of moving to another adjacent Node.
	 * <br />Typically, diagonal movements are 14, while movements horizontally or vertically are 10.
	 * <br />This movement cost should not be used on non-adjacent Nodes.
	 * 
	 * @param other Another adjacent node to move to.
	 * @return 10 or 14 respectively provided the provided Node is adjacent to the owner Node.
	 */
	public int getRawMovementCostTo(Node other) {
		if (this.permanentValue < 0) {
			System.err.println("Calling movement cost before determining perm value");
		}
		return (int) (10 * (Math.hypot(Math.abs(other.x - owner.x), Math.abs(other.y - owner.y))));
	}
	
	public int getMovementCostTo(Node other) {
		if (this.permanentValue < 0) {
			System.err.println("Calling movement cost before determining perm value");
		}
		return this.permanentValue + (int) (10 * (Math.hypot(Math.abs(other.x - owner.x), Math.abs(other.y - owner.y))));
	}
	
	public void reset() {
		this.temporaryValue = -1;
		this.permanentValue = -1;
	}
}
