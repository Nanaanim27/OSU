package edu.osu.cse.misc.graph.plotting.impl.components.tabs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.osu.cse.misc.graph.plotting.impl.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.impl.components.equationform._2d.AbstractEquationForm2D;
import edu.osu.cse.misc.graph.plotting.impl.components.equationform._2d.Equation2D;
import edu.osu.cse.misc.graph.plotting.wrappers.function._2d.AbstractFunction2D;

public class AddFunction2D extends JPanel {

	private AbstractEquationForm2D<? extends AbstractFunction2D> functionForm;
	private GridBagConstraints gbc = new GridBagConstraints();
	private EquationPlotter plotInstance;

	private JRadioButton polynomial = new JRadioButton("Polynomial") {{
		setSelected(true);
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction2D.this.setForm(Equation2D.POLYNOMIAL.getForm());
				}
			}
		});
	}};
	private JRadioButton parametric = new JRadioButton("Parametric") {{ 
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction2D.this.setForm(Equation2D.PARAMETRIC.getForm());
				}
			}
		});
	}};
	private JRadioButton polar = new JRadioButton("Polar") {{ 
		setFocusPainted(false); 
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction2D.this.setForm(Equation2D.POLAR.getForm());
				}
			}
		});
	}};
	
	private ButtonGroup buttons = new ButtonGroup() {{
		add(AddFunction2D.this.polynomial);
		add(AddFunction2D.this.parametric);
		add(AddFunction2D.this.polar);
	}};

	/**
	 * Constructs the AddFunction tab
	 */
	public AddFunction2D(EquationPlotter instance) {
		this.plotInstance = instance;
		this.setLayout(new GridBagLayout());

		this.gbc = new GridBagConstraints();
		this.gbc.fill = GridBagConstraints.NONE;
		this.gbc.anchor = GridBagConstraints.NORTHWEST;

		this.gbc.weighty = 0D;
		this.gbc.weightx = 1D;
		this.gbc.gridy = 0;
		this.gbc.gridx = 0;
		this.add(buildButtonContainer(), this.gbc);
		
		this.setForm(Equation2D.POLYNOMIAL.getForm());
	}

	private JPanel buildButtonContainer() {
		JPanel buttonContainer = new JPanel(new GridBagLayout());

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.NONE;
		gbc2.anchor = GridBagConstraints.NORTHWEST;
		
		gbc2.weightx = 0D;
		gbc2.weighty = 0D;
		gbc2.gridy = 0;
		buttonContainer.add(this.polynomial, gbc2);
		
		gbc2.gridy++;
		buttonContainer.add(this.parametric, gbc2);
		
		gbc2.gridy++;
		buttonContainer.add(this.polar, gbc2);

		return buttonContainer;
	}

	private void setForm(AbstractEquationForm2D<? extends AbstractFunction2D> form) {
		if (this.functionForm != null) {
			this.remove(this.functionForm);
		}
		
		this.gbc.gridy = 1;
		this.gbc.gridx = 0;
		this.gbc.gridy++;
		this.gbc.weighty = 1D;
		this.gbc.weightx = 1D;
		this.functionForm = form;
		if (!form.isLinked()) {
			form.linkTo(this.plotInstance.getGraphPanel(), this.plotInstance);
		}
		this.add(form, this.gbc);
		
		this.repaint();
		this.revalidate();
		this.plotInstance.operationsPane.revalidate();
		form.setBorder(BorderFactory.createLineBorder(Color.CYAN));
	}
}
