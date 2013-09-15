package edu.osu.cse.misc.graph.plotting.impl.plotter.components.equationform;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.plotting.impl.plotter.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph.GraphPanel2D;

public abstract class AbstractEquationForm<K extends AbstractFunction2D> extends JPanel {
	
	protected ArrayList<K> functions = new ArrayList<>();
	
	protected GraphPanel2D graphPanel;
	protected EquationPlotter plotInstance;
	protected abstract JLabel getVariableDescriptionLabel();
	
	public void linkTo(GraphPanel2D graphPanel, EquationPlotter plotInstance) {
		this.graphPanel = graphPanel;
		this.plotInstance = plotInstance;
	}
	
	public GraphPanel2D getGraphPanelLink() {
		return this.graphPanel;
	}
	
	public EquationPlotter getPlotterLink() {
		return this.plotInstance;
	}
	
	public boolean isLinked() {
		return this.graphPanel != null;
	}
	
	public void submitFunction(K function) {
		if (!functions.contains(function)) {
			functions.add(function);
		}
	}
	
	protected abstract class AbstractEquationField extends JPanel {
		
		protected abstract JLabel getLeftSideLabel();
		protected abstract KeyAdapter getOnKeyEvent();
		protected JTextField rightSideField;
		
		protected JTextField getRightSideField() {
			if (this.rightSideField == null) {
				rightSideField = new JTextField(15);
				rightSideField.addKeyListener(this.getOnKeyEvent());
			}
			return rightSideField;
		}
	}
}
