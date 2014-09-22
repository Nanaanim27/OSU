package edu.osu.cse.misc.graph.plotting.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.osu.cse.misc.graph.plotting.impl.components.tabs.AddFunction2D;
import edu.osu.cse.misc.graph.plotting.impl.components.tabs.AddFunction3D;
import edu.osu.cse.misc.graph.plotting.impl.components.tabs.Overview;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.GraphPanel2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._3d.GraphPanel3D;
import edu.osu.cse.misc.util.Resources.Resource;


public class EquationPlotter extends JFrame {

	private JPanel container;
	private GridBagConstraints gbc = new GridBagConstraints();

	public JTabbedPane operationsPane = new JTabbedPane();

	private GraphPanel2D graphPanel2D = new GraphPanel2D(-20, 20, -20, 20) {{
		setXInterval(2f);
		setYInterval(2f);
	}};

	private GraphPanel3D graphPanel3D = new GraphPanel3D(-20, 20, -20, 20, -20, 20) {{
		setXInterval(2f);
		setYInterval(2f);
		setZInterval(2f);
	}};

	private JPanel noGraphFiller = new JPanel() {{
		setPreferredSize(graphPanel2D.getPreferredSize());
	}};

	public Overview overview;
	public AddFunction2D addFunction2D;
	public AddFunction3D addFunction3D;

	public EquationPlotter() {
		this.container = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

		buildTabbedPane();
		container.add(this.operationsPane, gbc);
		def(container);
	}

	private void buildTabbedPane() {
		this.operationsPane.addTab("Overview", (this.overview = new Overview(this)));
		this.operationsPane.add("Add 2D", (this.addFunction2D = new AddFunction2D(this)));
	}


	/** Default JFrame operations */
	private void def(JPanel container) {
		this.setTitle("Equation Plotter");
		this.setIconImage(Resource.FX.getImage());
		this.container.setPreferredSize(new Dimension(800, 600));
		this.add(container);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setVisible(true);
	}

	public GraphPanel2D getGraphPanel2D() {
		return this.graphPanel2D;
	}

	public GraphPanel3D getGraphPanel3D() {
		return this.graphPanel3D;
	}

	public static void main(String[] args) {
		new EquationPlotter();
	}

}
