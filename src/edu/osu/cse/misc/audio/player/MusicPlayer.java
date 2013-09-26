package edu.osu.cse.misc.audio.player;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MusicPlayer extends JFrame {

	private static final int WIDTH = 800, HEIGHT = 500;
	private static MusicPlayer instance;
	
	public MusicPlayer() {
		instance = this;
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		def(container);
	}
	
	public static MusicPlayer getInstance() {
		return instance;
	}
	
	private void def(JPanel container) {
		this.add(container);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
