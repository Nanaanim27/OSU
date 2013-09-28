package edu.osu.cse.misc.swing.testing;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefaultFrame extends JFrame {

	public DefaultFrame(JComponent container) {
		JPanel contents = new JPanel();
		contents.add(container);
		this.add(contents);
		go();
	}
	
	public void go() {
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
