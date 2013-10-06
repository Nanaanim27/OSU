package edu.osu.cse.misc.graph.pathfinding.impl.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class GridPanel extends JPanel {

	public Grid grid;
	public NodeQuery drawPath;

	public GridPanel(Grid grid) {
		this.grid = grid;
		setPreferredSize(new Dimension((grid.width * Node.SIZE) + 1, (grid.height * Node.SIZE) + 1));
		setMinimumSize(getPreferredSize());
		MouseAdapter ma = listener(grid);
		addMouseListener(ma);
		addMouseMotionListener(ma);
	}

	@Override
	protected void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		this.grid.draw((Graphics2D) gfx);
		if (this.drawPath != null) {
			for (Node node : this.drawPath) {
				if (node != null) {
					node.type = NodeType.PATH;
					node.draw((Graphics2D) gfx);
				}
			}
		}
	}

	private MouseAdapter listener(final Grid grid) {
		return new MouseAdapter() {

			private Node dragTarget;
			private LinkedHashSet<Node> covered = new LinkedHashSet<>();

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					this.covered.clear();
					this.dragTarget = grid.getNode(e.getPoint());
					if (this.dragTarget != null) {
						if (this.dragTarget.type == NodeType.UNBLOCKED) {
							this.dragTarget.type = NodeType.BLOCKED;
						}
						else if (this.dragTarget.type == NodeType.BLOCKED) {
							this.dragTarget.type = NodeType.UNBLOCKED;
						}
						if (this.dragTarget != null) {
							GridPanel.this.drawPath = null;
							for (Node n : grid.getNodes()) {
								if (n.type == NodeType.PATH)
									n.type = NodeType.UNBLOCKED;
							}
							repaint();
						}
					}
				}
			};

			@Override
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (this.dragTarget != null) {
						Node nodeAtMouse = grid.getNode(e.getPoint());
						if (!this.covered.contains(nodeAtMouse)) {
							if (nodeAtMouse != null && nodeAtMouse != this.dragTarget) {
								if ((this.dragTarget.type == NodeType.START || this.dragTarget.type == NodeType.FINISH) && (nodeAtMouse.type != NodeType.FINISH && nodeAtMouse.type != NodeType.START)) {
									nodeAtMouse.type = this.dragTarget.type;
									this.dragTarget.type = NodeType.UNBLOCKED;
									this.dragTarget = nodeAtMouse;
									if (this.dragTarget.type == NodeType.START) {
										grid.start = nodeAtMouse.aStarProperties.grid.start = nodeAtMouse;
									}
									else if (this.dragTarget.type == NodeType.FINISH) {
										grid.finish = nodeAtMouse.aStarProperties.grid.start = nodeAtMouse;
									}
								}
								else if (nodeAtMouse.type == NodeType.UNBLOCKED) {
									nodeAtMouse.type = NodeType.BLOCKED;
									this.dragTarget = nodeAtMouse;
								}
								else if (nodeAtMouse.type == NodeType.BLOCKED) {
									nodeAtMouse.type = NodeType.UNBLOCKED;
									this.dragTarget = nodeAtMouse;
								}
								repaint();
								this.covered.add(nodeAtMouse);
							}
						}
					}
				}
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					Node nodeAtMouse = grid.getNode(e.getPoint());
					if (nodeAtMouse != null && nodeAtMouse.type != NodeType.START && nodeAtMouse.type != NodeType.FINISH) {
						nodeAtMouse.type = nodeAtMouse.type != NodeType.CHECKPOINT ? NodeType.CHECKPOINT : NodeType.UNBLOCKED;
						repaint();
					}
				}
			}
		};
	}


}
