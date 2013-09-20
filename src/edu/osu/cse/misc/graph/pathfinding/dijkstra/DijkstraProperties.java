package edu.osu.cse.misc.graph.pathfinding.dijkstra;

import java.util.HashMap;

import edu.osu.cse.misc.graph.pathfinding.wrappers.NodeProperties;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

public class DijkstraProperties extends NodeProperties {

	/** Maps a DijkstraPath instance to its respective Grid, which contains a set of Nodes and their properties */
	private static HashMap<DijkstraPath, HashMap<Node, DijkstraProperties>> instanceMap = new HashMap<>();
	
	/** Value assigned to possible Nodes to visit in the future. */
	public int temporaryValue = -1;
	
	/** Value assigned to visited Node. Represents the total movement cost from the start to this Node through each of the previously visited Nodes. */
	public int permanentValue = -1;
	
	public DijkstraProperties(Node owner, Grid grid) {
		super(owner, grid);
	}
	
	/**
	 * Fetches the set of Properties for the given DijkstraPath and Node.
	 * 
	 * @param pathInstance An instantiated DijkstraPath
	 * @param owner The Node to get the Properties for.
	 * @return The DijstraProperties for the given Node.
	 */
	public static DijkstraProperties getInstanceForPath(DijkstraPath pathInstance, Node owner) {
		return instanceMap.get(pathInstance).get(owner);
	}
	
	/**
	 * Registers a single node to the given DijkstraPath's map of Properties.
	 * 
	 * @param owner The Node to register.
	 * @param grid The Grid containing the given Node.
	 * @param pathInstance The DijkstraPath represented by the given Grid.
	 */
	public static void registerProperties(Node owner, Grid grid, DijkstraPath pathInstance) {
		if (instanceMap.get(pathInstance) == null) {
			instanceMap.put(pathInstance, new HashMap<Node, DijkstraProperties>());
		}
		instanceMap.get(pathInstance).put(owner, new DijkstraProperties(owner, grid));
	}
	
	/**
	 * Registers each Node in a Grid for the given DijkstraPath's map of Properties.
	 * 
	 * @param grid The Grid containing each Node to be registered.
	 * @param pathInstance The DijkstraPath represented by the given Grid.
	 */
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
		return (int) (10 * (Math.hypot(Math.abs(other.x - this.owner.x), Math.abs(other.y - this.owner.y))));
	}
	
	/**
	 * Calculates the total movement cost from the start Node to the given Node.
	 * <br />For this calculation to function properly, <tt>this</tt> Node must hold a valid {@link #permanentValue},
	 * and the <tt>other</tt> Node must be adjacent to <tt>this</tt> Node.
	 * 
	 * @param other
	 * @return
	 */
	public int getMovementCostTo(Node other) {
		if (this.permanentValue < 0) {
			System.err.println("Calling movement cost before determining perm value");
		}
		return this.permanentValue + (int) (10 * (Math.hypot(Math.abs(other.x - this.owner.x), Math.abs(other.y - this.owner.y))));
	}
	
	/**
	 * Resets the values of this DijkstraProperty.
	 * <br />Necessary between path computations because the Nodes of a Grid hold one instance of DijkstraProperties, 
	 * and in order for a DijkstaPath to compute properly, both {@link #temporaryValue} and {@link #permanentValue}
	 * must be initialized as an invalid number. In this case, -1.
	 */
	public void reset() {
		this.temporaryValue = -1;
		this.permanentValue = -1;
	}
}
