package edu.osu.cse.misc.graph.pathfinding.wrappers;

import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;

/**
 * A Path between two given Nodes.
 */
public abstract class Path {

	protected Node[] nodes;
	private Node currentStep;
	
	public abstract Node[] toNodeArray(boolean useDiagonal);
	
	/**
	 * Increments and returns the next node in this path. The current Node of the path is updated each time this method is called.
	 * <br />The current Node can be reset by calling {@link #reset}
	 * 
	 * @param useDiagonal Only used if this Path has not been generated yet.
	 * @return The next Node along the path.
	 */
	public Node step(boolean useDiagonal) {
		boolean returnNext = false;
		for (Node node : this.toNodeArray(useDiagonal)) {
			if (this.currentStep == null || returnNext) {
				return (this.currentStep = node);
			}
			else if (this.currentStep == node) {
				returnNext = true;
			}
		}
		return (this.currentStep = this.nodes[nodes.length-1]);
	}
	
	public void reset() {
		this.currentStep = null;
	}
	
}
