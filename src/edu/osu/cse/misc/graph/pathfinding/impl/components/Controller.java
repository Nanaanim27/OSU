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
import edu.osu.cse.misc.graph.pathfinding.dijkstra.DijkstraPath;
import edu.osu.cse.misc.graph.pathfinding.dijkstra.DijkstraProperties;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.Node;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeQuery;
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;
import edu.osu.cse.misc.work.Worker;

public class Controller extends JPanel {

	private GridPanel gridPanel;
	private JRadioButton aStar = new JRadioButton("A Star") {{
		setSelected(true);
		setFocusPainted(false);
	}};
	private JRadioButton dijkstra = new JRadioButton("Dijkstra") {{
		setFocusPainted(false);
	}};
	
	private ButtonGroup group = new ButtonGroup() {{
		add(Controller.this.aStar);
		add(Controller.this.dijkstra);
	}};

	private JButton findPath = new JButton("Find Path") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.executeRunnable(new Runnable() {
					public void run() {
						clear();
						if (Controller.this.aStar.isSelected()) {
							AStarPath path = new AStarPath(Controller.this.gridPanel.grid);
							for (Node node : Controller.this.gridPanel.grid.getNodes()) {
								if (node.type == NodeType.CHECKPOINT) {
									path.addCheckpoint(node);
								}
							}
							NodeQuery nodes = path.toNodeQuery(Controller.this.useDiagonals.isSelected());
							if (nodes != null && nodes.size() > 0) {
								System.out.println("Nodes in computed path: " + nodes.size());
								Controller.this.gridPanel.drawPath = nodes;
								Controller.this.gridPanel.repaint();
							}
						}
						else if (Controller.this.dijkstra.isSelected()) {
							DijkstraPath path = new DijkstraPath(Controller.this.gridPanel.grid);
							for (Node n : Controller.this.gridPanel.grid.getNodes()) {
								DijkstraProperties.registerProperties(n, Controller.this.gridPanel.grid, path);
								n.dijkstraProperties(path).reset();
								if (n.type == NodeType.CHECKPOINT) {
									path.addCheckpoint(n);
								}
							}
							NodeQuery nodes = path.toNodeQuery(Controller.this.useDiagonals.isSelected());
							if (nodes != null && nodes.size() > 0) {
								System.out.println("Nodes in computed path: " + nodes.size());
								Controller.this.gridPanel.drawPath = nodes;
								Controller.this.gridPanel.repaint();
							}
						}
					}
				});
			}
		});
	}};

	private JButton resetGrid = new JButton("Reset grid") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.executeRunnable(new Runnable() {
					public void run() {
						clear();
						Node[] nodes = Controller.this.gridPanel.grid.getNodes();
						for (int i = 0; i < nodes.length; i++) {
							if (i == 0) {
								nodes[i].type = NodeType.START;
								Controller.this.gridPanel.grid.start = nodes[i];
							}
							else if (i == nodes.length-1) {
								nodes[i].type = NodeType.FINISH;
								Controller.this.gridPanel.grid.finish = nodes[i];
							}
							else {
								nodes[i].type = NodeType.UNBLOCKED;
							}
						}
						repaint();
					}
				});
			}
		});
	}};

	private JButton clear = new JButton("Clear Path") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.executeRunnable(new Runnable() {
					public void run() {
						clear();
					}
				});
			}
		});
	}};

	private void clear() {
		if (this.gridPanel.drawPath != null) {
			for (Node n : this.gridPanel.drawPath) {
				n.type = NodeType.UNBLOCKED;
			}
		}
		for (Node n : this.gridPanel.grid.getNodes()) {
			if (n.type == NodeType.START) {
				this.gridPanel.grid.start = n;
			}
			else if (n.type == NodeType.FINISH) {
				this.gridPanel.grid.finish = n;
			}
		}
		this.gridPanel.drawPath = null;
		this.gridPanel.repaint();
	}

	private JCheckBox useDiagonals = new JCheckBox("Use Diagonals") {{
		setSelected(true);
	}};

	public Controller(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(this.aStar);
		this.add(this.dijkstra);
		this.add(this.useDiagonals);
		this.add(this.findPath);
		this.add(this.resetGrid);
		this.add(this.clear);
	}



}
