package edu.osu.cse.misc.graph.pathfinding.wrappers;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * The properties of a Node with respect to the Grid that contains it.
 */
public abstract class NodeProperties {

	public final Grid grid;
	public final Node owner;
	
	/**
	 * Constructs the properties for the given Node.
	 * 
	 * @param owner The Node of which these properties represent.
	 * @param grid The Grid of which <tt>owner</tt> belongs to.
	 */
	public NodeProperties(Node owner, Grid grid) {
		this.grid = grid;
		this.owner = owner;
	}
	
}
