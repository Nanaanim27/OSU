package edu.osu.cse.misc.graph.pathfinding.wrappers.node;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

public class NodeQuery extends LinkedList<Node> {

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
	
	public NodeQuery sort(Comparator<Node> comparator) {
		TreeSet<Node> sortedSet = new TreeSet<>(comparator);
		sortedSet.addAll(this);
		this.clear();
		this.addAll(sortedSet);
		return this;
	};
	
	
}
