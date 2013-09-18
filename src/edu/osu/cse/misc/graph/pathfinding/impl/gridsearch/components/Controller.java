package edu.osu.cse.misc.graph.pathfinding.impl.gridsearch.components;

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
import edu.osu.cse.misc.graph.pathfinding.wrappers.node.NodeType;
import edu.osu.cse.misc.work.Worker;

public class Controller extends JPanel {

	private GridPanel gridPanel;
	private JRadioButton aStar = new JRadioButton("A Star") {{
		setSelected(true);
		setFocusPainted(false);
	}};
	private JRadioButton dijkstra = new JRadioButton("Dijkstra") {{
		setFocusable(false);
	}};

	private ButtonGroup group = new ButtonGroup() {{
		add(aStar);
		add(dijkstra);
	}};

	private JButton findPath = new JButton("Find Path") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.executeRunnable(new Runnable() {
					public void run() {
						clear();
						if (aStar.isSelected()) {
							AStarPath path = new AStarPath(gridPanel.grid);
							for (Node node : gridPanel.grid.getNodes()) {
								if (node.type == NodeType.CHECKPOINT) {
									path.addCheckpoint(node);
								}
							}
							Node[] nodes = path.toNodeArray(useDiagonals.isSelected());
							if (nodes != null && nodes.length > 0) {
								System.out.println("Nodes in computed path: " + nodes.length);
								gridPanel.drawPath = nodes;
								gridPanel.repaint();
							}
						}
						else if (dijkstra.isSelected()) {
							try {
								DijkstraPath path = new DijkstraPath(gridPanel.grid);
								for (Node n : gridPanel.grid.getNodes()) {
									DijkstraProperties.registerProperties(n, gridPanel.grid, path);
									n.dijkstraProperties(path).reset();
									if (n.type == NodeType.CHECKPOINT) {
										path.addCheckpoint(n);
									}
								}
								Node[] nodes = path.toNodeArray(useDiagonals.isSelected());
								if (nodes != null && nodes.length > 0) {
									System.out.println("Nodes in computed path: " + nodes.length);
									gridPanel.drawPath = nodes;
									gridPanel.repaint();
								}
							} catch (Exception err) {err.printStackTrace();}
						}
					}
				});
			}
		});
	}};
	
	/*private Node[] includeEndpoints(Node[] noEndpoints) {
		Node[] included = new Node[noEndpoints.length + 2];
		included[0] = this.gridPanel.grid.start;
		included[included.length-1] = this.gridPanel.grid.finish;
		
		for (int i = 1; i < included.length-1; i++) {
			included[i] = noEndpoints[i-1];
		}
		return included;
	}*/

	private JButton resetGrid = new JButton("Reset grid") {{
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.executeRunnable(new Runnable() {
					public void run() {
						clear();
						Node[] nodes = gridPanel.grid.getNodes();
						for (int i = 0; i < nodes.length; i++) {
							if (i == 0) {
								nodes[i].type = NodeType.START;
								gridPanel.grid.start = nodes[i];
							}
							else if (i == nodes.length-1) {
								nodes[i].type = NodeType.FINISH;
								gridPanel.grid.finish = nodes[i];
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
		if (gridPanel.drawPath != null) {
			for (Node n : gridPanel.drawPath) {
				n.type = NodeType.UNBLOCKED;
			}
		}
		for (Node n : gridPanel.grid.getNodes()) {
			if (n.type == NodeType.START) {
				gridPanel.grid.start = n;
			}
			else if (n.type == NodeType.FINISH) {
				gridPanel.grid.finish = n;
			}
		}
		gridPanel.drawPath = null;
		gridPanel.repaint();
	}

	private JCheckBox useDiagonals = new JCheckBox("Use Diagonals") {{
		setSelected(true);
	}};

	public Controller(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(aStar);
		this.add(dijkstra);
		this.add(useDiagonals);
		this.add(findPath);
		this.add(resetGrid);
		this.add(clear);
	}



}
