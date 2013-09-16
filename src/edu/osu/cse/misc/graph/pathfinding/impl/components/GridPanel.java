package edu.osu.cse.misc.graph.pathfinding.impl.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import edu.osu.cse.misc.graph.pathfinding.wrappers.grid.Grid;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class GridPanel extends JPanel {

	public Grid grid;
	public Node[] drawPath;
	
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
		grid.draw((Graphics2D) gfx);
		if (this.drawPath != null) {
			for (Node node : this.drawPath) {
				node.type = NodeType.PATH;
				node.draw((Graphics2D) gfx);
			}
		}
	}
	
	private MouseAdapter listener(final Grid grid) {
		return new MouseAdapter() {

			private Node dragTarget;

			@Override
			public void mousePressed(MouseEvent e) {
				dragTarget = grid.getNode(e.getPoint());
			};

			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragTarget != null) {
					Node nodeAtMouse = grid.getNode(e.getPoint());
					if (nodeAtMouse != null && nodeAtMouse != dragTarget) {
						if ((dragTarget.type == NodeType.START || dragTarget.type == NodeType.FINISH) && (nodeAtMouse.type != NodeType.FINISH && nodeAtMouse.type != NodeType.START)) {
							nodeAtMouse.type = dragTarget.type;
							dragTarget.type = NodeType.UNBLOCKED;
							dragTarget = nodeAtMouse;
							if (dragTarget.type == NodeType.START) {
								grid.start = nodeAtMouse.aStarProperties.start = nodeAtMouse;
							}
							else if (dragTarget.type == NodeType.FINISH) {
								grid.finish = nodeAtMouse.aStarProperties.finish = nodeAtMouse;
							}
						}
						else if (nodeAtMouse.type == NodeType.UNBLOCKED) {
							nodeAtMouse.type = NodeType.BLOCKED;
							dragTarget = nodeAtMouse;
						}
						else if (nodeAtMouse.type == NodeType.BLOCKED) {
							nodeAtMouse.type = NodeType.UNBLOCKED;
							dragTarget = nodeAtMouse;
						}
						repaint();
					}
				}
			};

			@Override
			public void mouseClicked(MouseEvent e) {
				Node target = grid.getNode(e.getPoint());
				if (target.type == NodeType.UNBLOCKED) {
					target.type = NodeType.BLOCKED;
				}
				else if (target.type == NodeType.BLOCKED) {
					target.type = NodeType.UNBLOCKED;
				}
				repaint();
			};
		};
	}

	
}
