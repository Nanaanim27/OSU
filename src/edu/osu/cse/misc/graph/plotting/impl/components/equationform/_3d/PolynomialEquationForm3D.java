package edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.PolynomialFunction3D;

public class PolynomialEquationForm3D extends AbstractEquationForm3D<PolynomialFunction3D> {

	PolynomialEquationForm3D() {
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(this.getVariableDescriptionLabel());
		formContainer.add(new PolynomialEquationField());

		this.add(formContainer);
	}

	@Override
	protected JLabel getVariableDescriptionLabel() {
		return new JLabel("Variables: x,y");
	}

	class PolynomialEquationField extends AbstractEquationField {

		public PolynomialEquationField() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
			this.add(this.getLeftSideLabel());
			this.add(this.getRightSideField());
		}

		@Override
		protected JLabel getLeftSideLabel() {
			return new JLabel("f(x,y)=");
		}

		@Override
		protected KeyAdapter getOnKeyEvent() {
			return new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						String submission = getRightSideField().getText();
						if (!submission.trim().equals("")) {
							PolynomialFunction3D eq = new PolynomialFunction3D(submission);
							if (eq.ensureValidity()) {
								getGraphPanelLink().addFunction(eq);
								getRightSideField().setText("");
								getPlotterLink().addFunction3D.addFunction("f(x,y)=" + submission);
							}
						}
					}
				}
			};
		}
	}
}
