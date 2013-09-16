package edu.osu.cse.misc.graph.pathfinding.wrappers.grid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

/**
 * Represents a matrix of Nodes.
 * <br />Intended to be implemented for pathfinding usage.
 */
public class Grid {

	public int width, height;

	private Node[][] nodes;
	public Node start, finish;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.nodes = new Node[height][width];

		this.start = this.nodes[0][0] = new Node(this, 0, 0);
		this.start.type = NodeType.START;

		this.finish = this.nodes[width-1][height-1] = new Node(this, width-1, height-1);
		this.finish.type = NodeType.FINISH;

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				Node next = new Node(this, w, h);
				if (!next.equals(this.start) && !next.equals(this.finish)) { 
					this.nodes[h][w] = new Node(this, w, h);
				}
			}
		}
	}

	public Node getNode(int width, int height) {
		if (width < 0 || height < 0)
			return null;
		else if (width >= this.width || height >= this.height)
			return null;
		return this.nodes[height][width];
	}

	public Node getNode(Point p) {
		int width = p.x / Node.SIZE;
		int height = p.y / Node.SIZE;
		return getNode(width, height);
	}

	public Node[] getNodes() {
		Node[] singleArrNodes = new Node[width*height];
		int index = 0;
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				singleArrNodes[index++] = this.nodes[h][w];
			}
		}
		return singleArrNodes;
	}

	/**
	 * The width of this Grid.
	 * 
	 * @return the number of Nodes horizontally on this Grid.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * The height of this Grid.
	 * 
	 * @return the number of Nodes vertically on this Grid.
	 */
	public int getHeight() {
		return this.width;
	}

	public void draw(Graphics2D g) {
		Node next;
		//Vertical lines
		for (int width = 0; width < this.nodes[0].length; width++) {
			for (int height = 0; height < this.nodes.length; height++) {
				next = this.getNode(width, height);
				if (next != null) {
					g.setColor(next.type.getColor());
					g.fillRect(next.x * Node.SIZE, next.y * Node.SIZE, Node.SIZE, Node.SIZE);
					g.setColor(Color.black);
					g.drawRect(next.x * Node.SIZE, next.y * Node.SIZE, Node.SIZE, Node.SIZE);
				}
				else {
					System.out.println("Null node at: " + width + ", " + height);
				}
			}
		}
	}



}
