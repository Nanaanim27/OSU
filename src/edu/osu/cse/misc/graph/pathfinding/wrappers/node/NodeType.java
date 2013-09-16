package edu.osu.cse.misc.graph.pathfinding.wrappers.node;

import java.awt.Color;

public enum NodeType {

	CHECKED(Color.yellow),
	START(Color.green),
	FINISH(Color.red),
	UNBLOCKED(Color.white),
	BLOCKED(Color.black),
	PATH(Color.blue);
	
	private Color color;
	
	NodeType(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
