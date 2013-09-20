package edu.osu.cse.misc.graph.pathfinding.depthfirst;

import java.util.HashMap;
import java.util.Stack;

import edu.osu.cse.misc.graph.pathfinding.wrappers.NodeProperties;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

public class DepthFirstProperties extends NodeProperties {

	/** Maps a Grid to its given Stack of nodes to be visited. */
	private static HashMap<Grid, Stack<Node>> stackMap = new HashMap<>();
	
	public DepthFirstProperties(Node owner, Grid grid) {
		super(owner, grid);
	}
	
	/**
	 * Fetches the Stack of Nodes for the given Grid.
	 * 
	 * @param owner
	 * @param grid
	 * @return
	 */
	public static Stack<Node> getNodeStackFor(Node owner, Grid grid) {
		if (stackMap.get(grid) == null) {
			stackMap.put(grid, new Stack<Node>());
		}
		return stackMap.get(grid);
	}
	
	public static void reset() {
		
	}
	
	
}
