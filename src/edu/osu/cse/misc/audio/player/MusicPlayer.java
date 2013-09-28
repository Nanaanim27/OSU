package edu.osu.cse.misc.audio.player;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MusicPlayer extends JFrame {

	private static final int WIDTH = 800, HEIGHT = 500;
	
	private static MusicPlayer instance;
	
	private GridBagConstraints gbc = new GridBagConstraints();

	public MusicPlayer() {
		instance = this;
		JPanel container = new JPanel(new GridBagLayout());
		this.gbc.anchor = GridBagConstraints.NORTHWEST;
		this.gbc.fill = GridBagConstraints.NONE;
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.weightx = 1D;
		this.gbc.weighty = 1D;
		
		
		
		def(container);
	}
	
	public static MusicPlayer getInstance() {
		return instance;
	}
	
	private void def(JPanel container) {
		this.setContentPane((JPanel) this.add(container));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MusicPlayer();
	}
}
