package edu.osu.cse.misc.graph.plotting.impl.plotter.components.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.osu.cse.misc.graph.plotting.impl.plotter.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.impl.plotter.components.Equation;
import edu.osu.cse.misc.graph.plotting.impl.plotter.components.equationform.AbstractEquationForm;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;

public class AddFunction extends JPanel {

	private AbstractEquationForm<? extends AbstractFunction2D> functionForm;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel filler = new JPanel();
	private EquationPlotter plotInstance;

	private JRadioButton 
	polynomial = new JRadioButton("Polynomial") {{ 
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction.this.setForm(Equation.POLYNOMIAL.getForm());
				}
			}
		});
	}},
	parametric = new JRadioButton("Parametric") {{ 
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction.this.setForm(Equation.PARAMETRIC.getForm());
				}
			}
		});
	}},
	polar = new JRadioButton("Polar") {{ 
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction.this.setForm(Equation.POLAR.getForm());
				}
			}
		});
	}};

	private ButtonGroup buttons = new ButtonGroup() {{
		add(polynomial);
		add(parametric);
		add(polar);
	}};

	/**
	 * Constructs the AddFunction tab
	 */
	public AddFunction(EquationPlotter instance) {
		this.plotInstance = instance;
		this.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weighty = 0D;
		gbc.weightx = 0D;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		this.add(buildButtonContainer(), gbc);
		this.setForm(Equation.POLYNOMIAL.getForm());
		instance.operationsPane.setMinimumSize(instance.operationsPane.getPreferredSize());
	}

	private JPanel buildButtonContainer() {
		JPanel buttonContainer = new JPanel(new GridBagLayout());

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.NONE;
		gbc2.anchor = GridBagConstraints.NORTHWEST;
		gbc2.weightx = 0D;
		gbc2.weighty = 0D;
		gbc2.gridy = 0;

		buttonContainer.add(polynomial, gbc2);
		gbc2.gridy++;
		buttonContainer.add(parametric, gbc2);
		gbc2.gridy++;
		buttonContainer.add(polar, gbc2);

		return buttonContainer;
	}

	private void setForm(AbstractEquationForm<? extends AbstractFunction2D> form) {
		if (this.functionForm != null) {
			this.remove(this.functionForm);
		}
		this.remove(this.filler);
		gbc.gridy = 1;
		gbc.gridy++;
		this.add((this.functionForm = form), gbc);
		gbc.gridy++;
		gbc.weighty = 1D;
		this.add(this.filler, gbc); //Filler
		this.repaint();
	}
}
