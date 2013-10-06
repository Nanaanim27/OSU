package edu.osu.cse.misc.graph.plotting.impl.components.equationform;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.PolarFunction2D;

public class PolarEquationForm extends AbstractEquationForm<PolarFunction2D> {

	public PolarEquationForm() {
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(this.getVariableDescriptionLabel());
		formContainer.add(new PolarEquationField());

		this.add(formContainer);
	}

	@Override
	protected JLabel getVariableDescriptionLabel() {
		return new JLabel("Variable: theta");
	}

	class PolarEquationField extends AbstractEquationField {

		public PolarEquationField() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
			this.add(this.getLeftSideLabel());
			this.add(this.getRightSideField());
		}

		@Override
		protected JLabel getLeftSideLabel() {
			return new JLabel("r=");
		}

		@Override
		protected KeyAdapter getOnKeyEvent() {
			return new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					String submission = getRightSideField().getText();
					if (!submission.trim().equals("")) {
						if (e.getKeyChar() == '\n') {
							PolarFunction2D eq = new PolarFunction2D(getRightSideField().getText());
							if (eq.ensureValidity()) {
								getGraphPanelLink().addFunction(eq);
								getRightSideField().setText("");
								getPlotterLink().overview.addFunction("r=" + submission);
							}
						}
					}
				}
			};
		}

	}

}
