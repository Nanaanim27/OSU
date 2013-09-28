package edu.osu.cse.misc.swing.testing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefaultFrame extends JFrame {

	public DefaultFrame(JComponent container) {
		JPanel contents = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		
		container.setBorder(BorderFactory.createLineBorder(Color.blue));
		contents.setBorder(BorderFactory.createLineBorder(Color.red));
		contents.add(container, gbc);
		
		this.setContentPane(contents);
		go();
	}
	
	public void go() {
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
