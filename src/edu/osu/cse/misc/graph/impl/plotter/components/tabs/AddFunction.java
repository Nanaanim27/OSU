package edu.osu.cse.misc.graph.impl.plotter.components.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AddFunction extends JPanel {

	private JRadioButton 
	polynomial = new JRadioButton("Polynomial") {{ setFocusPainted(false); }},
	parametric = new JRadioButton("Parametric") {{ setFocusPainted(false); }},
	polar = new JRadioButton("Polar") {{ setFocusPainted(false); }};

	private ButtonGroup buttons = new ButtonGroup() {{
		add(polynomial);
		add(parametric);
		add(polar);
	}};

	public AddFunction() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0D;
		gbc.weightx = 0D;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		this.add(buildButtonContainer(), gbc);
		
		gbc.gridy++;
		gbc.weighty = 1D;
		this.add(new JPanel(), gbc); //Filler
	}
	
	private JPanel buildButtonContainer() {
		JPanel buttonContainer = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 0D;
		gbc.weighty = 0D;
		gbc.gridy = 0;
		
		buttonContainer.add(polynomial, gbc);
		gbc.gridy++;
		buttonContainer.add(parametric, gbc);
		gbc.gridy++;
		buttonContainer.add(polar, gbc);
		
		return buttonContainer;
	}

}
