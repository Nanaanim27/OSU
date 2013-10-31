package edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.ParametricFunction3D;

public class ParametricEquationForm3D extends AbstractEquationForm3D<ParametricFunction3D> {

	private ParametricEquationField xT, yT, zT;

	ParametricEquationForm3D() {
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.add(this.getVariableDescriptionLabel());

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
						if (ParametricEquationForm3D.this.yT == null || ParametricEquationForm3D.this.zT == null)
							return;
						if (e.getKeyChar() == '\n' || e.getKeyChar() == '\t') {
							ParametricEquationForm3D.this.yT.getRightSideField().requestFocus();
							ParametricEquationForm3D.this.yT.getRightSideField().setSelectionStart(0);
							ParametricEquationForm3D.this.yT.getRightSideField().setSelectionEnd(ParametricEquationForm3D.this.yT.getRightSideField().getText().length());
						}
					}
				};
			}
		};
		
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
						if (ParametricEquationForm3D.this.xT == null || ParametricEquationForm3D.this.zT == null || !ParametricEquationForm3D.this.isLinked())
							return;
						if (e.getKeyChar() == '\n' || e.getKeyChar() == '\t') {
							ParametricEquationForm3D.this.zT.getRightSideField().requestFocus();
							ParametricEquationForm3D.this.zT.getRightSideField().setSelectionStart(0);
							ParametricEquationForm3D.this.zT.getRightSideField().setSelectionEnd(ParametricEquationForm3D.this.zT.getRightSideField().getText().length());
						}
					}
				};
			}
		};
		
		this.zT = new ParametricEquationField() {
			
			@Override
			protected JLabel getLeftSideLabel() {
				return new JLabel("z(t)=");
			}
			
			@Override
			protected KeyAdapter getOnKeyEvent() {
				return new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if (ParametricEquationForm3D.this.zT == null || ParametricEquationForm3D.this.yT == null)
							return;
						String xTSubmission = ParametricEquationForm3D.this.xT.getRightSideField().getText();
						String yTSubmission = ParametricEquationForm3D.this.yT.getRightSideField().getText();
						String zTSubmission = ParametricEquationForm3D.this.zT.getRightSideField().getText();
						if (!xTSubmission.trim().equals("") && !yTSubmission.trim().equals("")) {
							if (e.getKeyChar() == '\n') {
								ParametricFunction3D eq = new ParametricFunction3D(xTSubmission, yTSubmission, zTSubmission);
								if (eq.ensureValidity()) {
									ParametricEquationForm3D.this.getGraphPanelLink().addFunction(eq);
									ParametricEquationForm3D.this.xT.getRightSideField().setText("");
									ParametricEquationForm3D.this.yT.getRightSideField().setText("");
									ParametricEquationForm3D.this.zT.getRightSideField().setText("");
									getPlotterLink().overview.addFunction("x(t)=" + xTSubmission + "; " + "y(t)=" + yTSubmission + "; " + "z(t)=" + zTSubmission);
								}
							}
						}
					}
				};
			}
			
		};

		formContainer.add(this.xT);
		formContainer.add(this.yT);
		formContainer.add(this.zT);
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
