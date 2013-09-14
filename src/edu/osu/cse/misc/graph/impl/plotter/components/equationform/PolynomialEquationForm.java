package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class PolynomialEquationForm extends AbstractEquationForm<PolynomialFunction2D> {

	public PolynomialEquationForm(GraphPanel2D graph) {
		super(graph);
		
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(new JLabel("Evaluate with respect to: x"));
		formContainer.add(new PolynomialEquationField());

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
		this.add(formContainer);
		this.add(this.getGraph());
	}

	@Override
	protected JLabel getVariableDescriptionLabel() {
		return new JLabel("Evaluate with respect to: x");
	}

	protected class PolynomialEquationField extends AbstractEquationField {

		private JTextField functionField;
		
		public PolynomialEquationField() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
			this.add(this.getLeftSideLabel());
			this.add(this.getRightSideField());
			this.getRightSideField().addKeyListener(this.getOnKeyEvent());
		}
		
		@Override
		protected JLabel getLeftSideLabel() {
			return new JLabel("f(x)=");
		}

		@Override
		protected JTextField getRightSideField() {
			if (this.functionField == null) {
				this.functionField = new JTextField(15);
			}
			return this.functionField;
		}
		
		@Override
		protected KeyAdapter getOnKeyEvent() {
			return new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						PolynomialFunction2D eq = new PolynomialFunction2D(getRightSideField().getText());
						if (eq.ensureValidity()) {
							System.out.println("Adding function to graph");
							getGraph().addFunction(eq);
						}
					}
				}
			};
		}
		
		
	}
	
	
}
