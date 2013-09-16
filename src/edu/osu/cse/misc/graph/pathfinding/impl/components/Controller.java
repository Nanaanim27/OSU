package edu.osu.cse.misc.graph.pathfinding.impl.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.osu.cse.misc.graph.pathfinding.astar.AStarPath;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;

public class Controller extends JPanel {

	private GridPanel gridPanel;
	private JRadioButton aStar = new JRadioButton("A Star") {{
		setSelected(true);
		setFocusPainted(false);
	}};

	private ButtonGroup group = new ButtonGroup() {{
		add(aStar);
	}};

	private JButton findPath = new JButton("Find Path") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aStar.isSelected()) {
					Node[] nodes = new AStarPath(gridPanel.grid.start, gridPanel.grid.finish).toNodeArray(useDiagonals.isSelected());
					if (nodes != null && nodes.length > 0) {
						System.out.println("Nodes in computed path: " + nodes.length);
						gridPanel.drawPath = nodes;
						gridPanel.repaint();
					}
				}
				//else if (dijkstra.isSelected()) { }
			}
		});
	}};

	private JButton resetGrid = new JButton("Reset grid") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				Node[] nodes = gridPanel.grid.getNodes();
				for (int i = 0; i < nodes.length; i++) {
					if (i == 0) {
						nodes[i].type = NodeType.START;
					}
					else if (i == nodes.length-1) {
						nodes[i].type = NodeType.FINISH;
					}
					else {
						nodes[i].type = NodeType.UNBLOCKED;
					}
				}
				repaint();
			}
		});
	}};
	
	private JButton clear = new JButton("Clear Path") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
	}};
	
	private void clear() {
		if (gridPanel.drawPath != null) {
			for (Node n : gridPanel.drawPath) {
				n.type = NodeType.UNBLOCKED;
			}
		}
		gridPanel.drawPath = null;
		gridPanel.repaint();
	}

	private JCheckBox useDiagonals = new JCheckBox("Use Diagonals");

	public Controller(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(aStar);
		this.add(findPath);
		this.add(resetGrid);
		this.add(clear);
	}



}
