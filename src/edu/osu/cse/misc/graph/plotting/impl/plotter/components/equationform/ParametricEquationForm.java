package edu.osu.cse.misc.graph.plotting.impl.plotter.components.equationform;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.impl.plotter.components.equationform.PolynomialEquationForm.PolynomialEquationField;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.ParametricFunction2D;

public class ParametricEquationForm extends AbstractEquationForm<ParametricFunction2D> {

	private ParametricEquationField xT, yT;

	public ParametricEquationForm() {
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(this.getVariableDescriptionLabel());

		yT = new ParametricEquationField() {

			@Override
			protected JLabel getLeftSideLabel() {
				return new JLabel("y(t)=");
			}

			@Override
			protected KeyAdapter getOnKeyEvent() {
				return new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (xT == null || !ParametricEquationForm.this.isLinked())
							return;
						String xTSubmission = xT.getRightSideField().getText();
						String yTSubmission = yT.getRightSideField().getText();
						if (!xTSubmission.trim().equals("") && !yTSubmission.trim().equals("")) {
							if (e.getKeyChar() == '\n') {
								ParametricFunction2D eq = new ParametricFunction2D(
										xTSubmission,
										yTSubmission);
								if (eq.ensureValidity()) {
									ParametricEquationForm.this.getGraphPanelLink().addFunction(eq);
									yT.getRightSideField().setText("");
									xT.getRightSideField().setText("");
									getPlotterLink().overview.addFunction("x(t)=" + xTSubmission + "; " + "y(t)=" + yTSubmission);
								}
							}
						}
					}
				};
			}
		};
		xT = new ParametricEquationField() {

			@Override
			protected JLabel getLeftSideLabel() {
				return new JLabel("x(t)=");
			}

			@Override
			protected KeyAdapter getOnKeyEvent() {
				return new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (yT == null)
							return;
						if (e.getKeyChar() == '\n' || e.getKeyChar() == '\t') {
							yT.getRightSideField().requestFocus();
							yT.getRightSideField().setSelectionStart(0);
							yT.getRightSideField().setSelectionEnd(yT.getRightSideField().getText().length());
						}
					}
				};
			}
		};

		formContainer.add(xT);
		formContainer.add(yT);
		this.add(formContainer);
	}

	@Override
	protected JLabel getVariableDescriptionLabel() {
		return new JLabel("Variable: t");
	}

	class ParametricEquationField extends AbstractEquationField {

		public ParametricEquationField() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
			this.add(this.getLeftSideLabel());
			this.add(this.getRightSideField());
		}

		@Override
		/** Must be overridden when implemented */
		protected JLabel getLeftSideLabel() {
			return null;
		}

		@Override
		/** Must be overridden when implemented */
		protected KeyAdapter getOnKeyEvent() {
			return null;
		}

	}

}
