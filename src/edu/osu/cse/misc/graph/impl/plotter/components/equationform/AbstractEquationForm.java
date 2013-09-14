package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public abstract class AbstractEquationForm<K extends AbstractFunction2D> extends JPanel {
	
	protected ArrayList<K> functions = new ArrayList<>();
	
	protected GraphPanel2D graphPanel;
	protected abstract JLabel getVariableDescriptionLabel();
	
	protected JButton submitButton() {
		
		JButton submit = new JButton("Graph Functions");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container parent = AbstractEquationForm.this.getParent();
				if (parent != null) {
					
				}
			}
		});
		
		return submit;
	}
	
	public void linkTo(GraphPanel2D graphPanel) {
		this.graphPanel = graphPanel;
	}
	
	public GraphPanel2D getGraphPanel() {
		return this.graphPanel;
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
		protected abstract JTextField getRightSideField();
		protected abstract KeyAdapter getOnKeyEvent();
		
	}
	
}
