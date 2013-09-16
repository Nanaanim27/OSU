package edu.osu.cse.misc.graph.pathfinding.wrappers;

import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * A Path between two given Nodes.
 */
public abstract class Path {

	public abstract Node[] toNodeArray(boolean useDiagonal);
	
}
