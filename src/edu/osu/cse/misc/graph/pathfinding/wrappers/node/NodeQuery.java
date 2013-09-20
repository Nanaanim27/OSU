package edu.osu.cse.misc.graph.pathfinding.wrappers.node;

import java.util.Arrays;
import java.util.LinkedList;

public class NodeQuery extends LinkedList<Node> implements Iterable<Node> {

	public NodeQuery(Node[] nodes) {
		this.addAll(Arrays.asList(nodes));
	}
	
	public NodeQuery filter(NodeType type) {
		for (int i = 0; i < this.size();) {
			Node n = this.get(i);
			if (n.type == type) {
				this.remove(i);
			}
			else {
				i++;
			}
		}
		return this;
	}
}
