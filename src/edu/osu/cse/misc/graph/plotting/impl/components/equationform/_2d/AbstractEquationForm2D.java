package edu.osu.cse.misc.graph.plotting.impl.components.equationform._2d;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.plotting.impl.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.GraphPanel2D;

public abstract class AbstractEquationForm2D<K extends AbstractFunction2D> extends JPanel {
	
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
		if (!this.functions.contains(function)) {
			this.functions.add(function);
		}
	}
	
	/**
	 * Any equation field that is constructed from this inner class 
	 * "belongs" to its pespective equation form. (It is constructed via
	 * and instance of AbstractEquationForm, and has access to that instance
	 * via AbstractEquationForm.this)
	 */
	protected abstract class AbstractEquationField extends JPanel {
		
		protected abstract JLabel getLeftSideLabel();
		protected abstract KeyAdapter getOnKeyEvent();
		protected JTextField rightSideField;
		
		protected JTextField getRightSideField() {
			if (this.rightSideField == null) {
				this.rightSideField = new JTextField(15);
				this.rightSideField.addKeyListener(this.getOnKeyEvent());
			}
			return this.rightSideField;
		}
	}
}
