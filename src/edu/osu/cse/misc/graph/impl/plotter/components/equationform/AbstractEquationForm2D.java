package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public abstract class AbstractEquationForm2D extends JPanel {

	protected abstract JLabel getVariableLabel();
	protected abstract EquationField[] getEquationFields();

	public AbstractEquationForm2D() {
		this.setLayout(new BorderLayout(3, 3));
		
		this.add(this.getVariableLabel(), BorderLayout.NORTH);
		
		JPanel fieldContainer = new JPanel();
		fieldContainer.setLayout(new BoxLayout(fieldContainer, BoxLayout.Y_AXIS));
		
		for (EquationField field : this.getEquationFields()) {
			fieldContainer.add(field);
		}
		this.add(fieldContainer, BorderLayout.CENTER);
		
	}
	
	protected static class EquationField extends JPanel {
		
		protected JTextField formulaField = new JTextField(15);
		
		protected EquationField(String variable) {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 0));
			this.add(new JLabel("f(x)="));
			this.add(new JTextField(15));
		}
		
		protected static ActionListener nextFieldListener(final EquationField targetField) {
			//TODO: Finish
			return null;
		}
	}
	
}
