package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;
import edu.osu.cse.misc.graph.wrappers.function._2d.ParametricFunction2D;
import edu.osu.cse.misc.graph.wrappers.function._2d.PolynomialFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;


public abstract class AbstractEquationForm2D extends JPanel {

	protected abstract String getVariable();

	protected abstract EquationField2D[] getEquationFields();
	protected GraphPanel2D graphPanel = new GraphPanel2D(-20, 20, -20, 20) {{
		setXInterval(2D);
		setYInterval(2D);
	}};

	protected void build() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));

		JPanel formPanel = new JPanel(new BorderLayout(3, 3));
		formPanel.setLayout(new BorderLayout(3, 3));

		formPanel.add(this.getVariableLabel(), BorderLayout.NORTH);

		JPanel fieldContainer = new JPanel();
		fieldContainer.setLayout(new BoxLayout(fieldContainer, BoxLayout.Y_AXIS));

		for (EquationField2D field : this.getEquationFields()) {
			if (field != null) {
				fieldContainer.add(field);
			}
		}
		formPanel.add(fieldContainer, BorderLayout.CENTER);

		this.add(formPanel);
		this.add(this.graphPanel);
	}

	protected JLabel getVariableLabel() {
		return new JLabel("Variable: " + this.getVariable());
	}

	protected class EquationField2D extends JPanel {

		protected JTextField formulaField = new JTextField(15);

		protected EquationField2D(String variable) {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 0));
			this.add(new JLabel("f(x)="));
			this.add(new JTextField(15));
		}

		public void setListener(KeyListener listener) {
			System.out.println("Setting listener for field: " + formulaField.getText());
			this.formulaField.addKeyListener(listener);
		}

		public KeyListener nextFieldListener(final EquationField2D targetField) {
			return new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						targetField.formulaField.requestFocus();
						targetField.formulaField.setSelectionStart(0);
						targetField.formulaField.setSelectionEnd(targetField.formulaField.getText().length());
					}
				}
			};
		}

		public KeyListener submitEquation(final GraphPanel2D graph) {
			return new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						System.out.println("Adding function to graph");
						AbstractFunction2D eq;
						switch(AbstractEquationForm2D.this.getVariable()) {
							case "x":
								eq = new PolynomialFunction2D(formulaField.getText());
								if (eq.ensureValidity()) {
									graph.addFunction(eq);
								}
								break;
							case "t":
								eq = new ParametricFunction2D(
										AbstractEquationForm2D.this.getEquationFields()[0].formulaField.getText(),
										AbstractEquationForm2D.this.getEquationFields()[1].formulaField.getText());
								if (eq.ensureValidity()) {
									graph.addFunction(eq);
								}
						}
					}
				}
			};
		}
	}
}
