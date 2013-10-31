package edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.plotting.impl.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._3d.GraphPanel3D;

public abstract class AbstractEquationForm3D<K extends AbstractFunction3D> extends JPanel {
	
	protected ArrayList<K> functions = new ArrayList<>();
	
	protected GraphPanel3D graphPanel;
	protected EquationPlotter plotInstance;
	protected abstract JLabel getVariableDescriptionLabel();
	
	public void linkTo(GraphPanel3D graphPanel, EquationPlotter plotInstance) {
		this.graphPanel = graphPanel;
		this.plotInstance = plotInstance;
	}
	
	public GraphPanel3D getGraphPanelLink() {
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
