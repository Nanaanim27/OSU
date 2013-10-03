package edu.osu.cse.misc.swing.armory;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.swing.armory.components.ArmoryForm;

/**
 * A simple utility to display WoW armory pages. 
 */
public class ArmoryViewer {

	private JFrame mainFrame = new JFrame("Armory Viewer");
	private JPanel mainContainer = new JPanel();
	
	private ArmoryForm form = new ArmoryForm();
	
	public ArmoryViewer() {
		this.mainContainer.setLayout(new BorderLayout(2, 2));
		this.mainContainer.add(this.form, BorderLayout.CENTER);
		this.mainFrame.add(this.mainContainer);
		this.mainFrame.pack();
		this.mainFrame.setLocationRelativeTo(null);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ArmoryViewer();
	}
	
}
