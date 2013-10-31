package edu.osu.cse.misc.graph.plotting.impl.components.equationform._2d;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.ParametricFunction2D;

public class ParametricEquationForm2D extends AbstractEquationForm2D<ParametricFunction2D> {

	private ParametricEquationField xT, yT;

	ParametricEquationForm2D() {
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(this.getVariableDescriptionLabel());

		this.yT = new ParametricEquationField() {

			@Override
			protected JLabel getLeftSideLabel() {
				return new JLabel("y(t)=");
			}

			@Override
			protected KeyAdapter getOnKeyEvent() {
				return new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (ParametricEquationForm2D.this.xT == null || !ParametricEquationForm2D.this.isLinked())
							return;
						String xTSubmission = ParametricEquationForm2D.this.xT.getRightSideField().getText();
						String yTSubmission = ParametricEquationForm2D.this.yT.getRightSideField().getText();
						if (!xTSubmission.trim().equals("") && !yTSubmission.trim().equals("")) {
							if (e.getKeyChar() == '\n') {
								ParametricFunction2D eq = new ParametricFunction2D(
										xTSubmission,
										yTSubmission);
								if (eq.ensureValidity()) {
									ParametricEquationForm2D.this.getGraphPanelLink().addFunction(eq);
									ParametricEquationForm2D.this.yT.getRightSideField().setText("");
									ParametricEquationForm2D.this.xT.getRightSideField().setText("");
									getPlotterLink().overview.addFunction("x(t)=" + xTSubmission + "; " + "y(t)=" + yTSubmission);
								}
							}
						}
					}
				};
			}
		};
		this.xT = new ParametricEquationField() {

			@Override
			protected JLabel getLeftSideLabel() {
				return new JLabel("x(t)=");
			}

			@Override
			protected KeyAdapter getOnKeyEvent() {
				return new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (ParametricEquationForm2D.this.yT == null)
							return;
						if (e.getKeyChar() == '\n' || e.getKeyChar() == '\t') {
							ParametricEquationForm2D.this.yT.getRightSideField().requestFocus();
							ParametricEquationForm2D.this.yT.getRightSideField().setSelectionStart(0);
							ParametricEquationForm2D.this.yT.getRightSideField().setSelectionEnd(ParametricEquationForm2D.this.yT.getRightSideField().getText().length());
						}
					}
				};
			}
		};

		formContainer.add(this.xT);
		formContainer.add(this.yT);
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
