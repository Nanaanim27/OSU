package edu.osu.cse.misc.graph.plotting.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.osu.cse.misc.graph.plotting.impl.components.tabs.AddFunction;
import edu.osu.cse.misc.graph.plotting.impl.components.tabs.Overview;
import edu.osu.cse.misc.graph.plotting.wrappers.graph.GraphPanel2D;


public class EquationPlotter extends JFrame {

	private static final String LOGO_PATH = "http://puu.sh/4scVA.png";
	
	public JTabbedPane operationsPane = new JTabbedPane() {{
		setPreferredSize(new Dimension(300, this.getPreferredSize().height));
	}};
	
	private GraphPanel2D graphPanel = new GraphPanel2D(-20, 20, -20, 20) {{
		setXInterval(2D);
		setYInterval(2D);
	}};
	
	public Overview overview;
	public AddFunction addFunction;
	
	public EquationPlotter() {
		JPanel container = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0D;
		gbc.weighty = 0D;
		gbc.gridx = 0;
		gbc.gridy= 0;
		
		buildTabbedPane();
		container.add(this.operationsPane, gbc);
		gbc.gridx++;
		gbc.weightx = 1D;
		gbc.weighty = 1D;
		this.graphPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		container.add(this.graphPanel, gbc);
		def(container);
	}
	
	private void buildTabbedPane() {
		this.operationsPane.addTab("Overview", (this.overview = new Overview(this)));
		this.operationsPane.add("Add", (this.addFunction = new AddFunction(this)));
		this.operationsPane.setBorder(BorderFactory.createLineBorder(Color.blue));
	}
	
	
	/** Default JFrame operations */
	private void def(JPanel container) {
		this.setTitle("Equation Plotter");
		try {
			this.setIconImage(ImageIO.read(new URL(LOGO_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(container);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public GraphPanel2D getGraphPanel() {
		return this.graphPanel;
	}
	
	public static void main(String[] args) {
		new EquationPlotter();
	}
	
}
