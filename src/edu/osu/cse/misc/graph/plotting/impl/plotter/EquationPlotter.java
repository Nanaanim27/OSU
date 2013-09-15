package edu.osu.cse.misc.graph.plotting.impl.plotter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.osu.cse.misc.graph.plotting.impl.plotter.components.tabs.AddFunction;
import edu.osu.cse.misc.graph.plotting.impl.plotter.components.tabs.Overview;
import edu.osu.cse.misc.graph.plotting.wrappers.graph.GraphPanel2D;


public class EquationPlotter extends JFrame {

	public JTabbedPane operationsPane = new JTabbedPane();
	
	private GraphPanel2D graphPanel = new GraphPanel2D(-20, 20, -20, 20) {{
		setXInterval(2D);
		setYInterval(2D);
	}};
	
	public EquationPlotter() {
		JPanel container = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = 0D;
		gbc.weighty = 0D;
		gbc.gridx = 0;
		gbc.gridy= 0;
		
		buildTabbedPane();
		container.add(operationsPane, gbc);
		gbc.gridx++;
		container.add(this.graphPanel, gbc);
		
		def(container);
	}
	
	private void buildTabbedPane() {
		operationsPane.addTab("Overview", new Overview(this));
		operationsPane.add("Add", new AddFunction(this));
		operationsPane.setBorder(BorderFactory.createLineBorder(Color.blue));
	}
	
	
	/** Default JFrame operations */
	private void def(JPanel container) {
		this.setTitle("Equation Plotter");
		this.add(container);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new EquationPlotter();
	}
	
}
