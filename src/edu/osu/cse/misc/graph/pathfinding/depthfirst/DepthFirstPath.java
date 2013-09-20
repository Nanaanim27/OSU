package edu.osu.cse.misc.graph.pathfinding.depthfirst;

import java.util.LinkedList;
import java.util.Stack;

import edu.osu.cse.misc.graph.pathfinding.wrappers.Path;
import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class DepthFirstPath extends Path {

	private Stack<Node> nodeStack = new Stack<>();
	private LinkedList<Node> visitedNodes = new LinkedList<>();

	public DepthFirstPath(Node start, Node finish, Grid grid) {
		super(start, finish, grid);
	}

	public DepthFirstPath(Grid grid) {
		super(grid);
	}

	@Override
	public NodeQuery toNodeQuery(boolean useDiagonal) {
		recurse(this.nodeStack.push(this.start), useDiagonal);
		return new NodeQuery(this.nodeStack.toArray(new Node[this.nodeStack.size()]));
	}

	private void recurse(Node n, boolean useDiagonal) {
		this.nodeStack.push(n);
		if (this.nodeStack.contains(this.finish)) {
			return;
		}
		NodeQuery neighbors = n.getNeighbors(useDiagonal).filter(NodeType.BLOCKED);
		if (neighbors.size() > 0) {
			for (Node neighbor : neighbors) {
				if (!this.nodeStack.contains(neighbor)) {
					recurse(neighbor, useDiagonal);
				}
			}
		}
	}


}
