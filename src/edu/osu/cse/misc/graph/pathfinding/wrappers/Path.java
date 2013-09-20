package edu.osu.cse.misc.graph.pathfinding.wrappers;

import java.util.Arrays;
import java.util.Collections;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;

/**
 * A Path between two given Nodes.
 */
public abstract class Path {

	protected Node start, finish;
	protected Grid grid;
	protected NodeQuery nodes;
	private Node currentStep;
	
	public Path(Node start, Node finish, Grid grid) {
		this.start = grid.start = start;
		this.finish = grid.finish = finish;
		this.grid = grid;
	}
	
	public Path(Grid grid) {
		this.start = grid.start;
		this.finish = grid.finish;
		this.grid = grid;
	}
	
	/**
	 * Computes a path between the start Node and finish Node of this Path.
	 * <br />In some implementations, the computed path will be in reverse order, meaning it will go from the end to the start.
	 * <br />You can use the {@link #reverse} method to reverse the direction of the Path.
	 * <br />Note that the current step will be reset upon reversing the Path.
	 * 
	 * @param useDiagonal Whether or not to allow the path to step diagonally.
	 * @return An array of Nodes that go from the start node to the end node.
	 */
	public abstract NodeQuery toNodeQuery(boolean useDiagonal);
	
	public void reverse() {
		if (this.nodes == null) {
			throw new NullPointerException("You have not called toNodeArray yet.");
		}
		Collections.reverse(Arrays.asList(this.nodes));
	}
	
	/**
	 * Increments and returns the next node in this path. The current Node of the path is updated each time this method is called.
	 * <br />The current Node can be reset by calling {@link #reset}
	 * 
	 * @param useDiagonal Only used if this Path has not been generated yet.
	 * @return The next Node along the path.
	 */
	public Node step(boolean useDiagonal) {
		boolean returnNext = false;
		for (Node node : this.toNodeQuery(useDiagonal)) {
			if (this.currentStep == null || returnNext) {
				return (this.currentStep = node);
			}
			else if (this.currentStep == node) {
				returnNext = true;
			}
		}
		return (this.currentStep = this.nodes.get(this.nodes.size()-1));
	}
	
	public void reset() {
		this.currentStep = null;
	}
	
}
