package edu.osu.cse.misc.audio.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.interfaces.Panel;
import edu.osu.cse.misc.audio.player.wrappers.tab.Library;
import edu.osu.cse.misc.audio.player.wrappers.tab.Playlists;
import edu.osu.cse.misc.audio.player.wrappers.tab.controller.Controller;
import edu.osu.cse.misc.swing.CollapsableJPanel;

public class MusicPlayer extends JFrame {

	private static MusicPlayer instance;
	
	private JPanel container = new JPanel(new BorderLayout(5, 5));
	private CollapsableJPanel west;
	private JPanel center, south;

	public MusicPlayer() {
		instance = this;
		
		this.west = new CollapsableJPanel("Overview");
		this.west.addContent(new Library().getPanel());
		this.west.addContent(new Playlists().getPanel());
		
		this.center = new JPanel();
		this.south = new Controller(this);
		
		this.west.setPreferredSize(new Dimension(100, 440));
		this.south.setPreferredSize(new Dimension(500, 40));
		this.center.setPreferredSize(new Dimension(500, 440));
		
		this.west.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.south.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.center.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		this.container.add(this.west, BorderLayout.WEST);
		this.container.add(this.center, BorderLayout.CENTER);
		this.container.add(this.south, BorderLayout.SOUTH);
		
		
		def(this.container);
	}
	
	public void setPanel(Panel panel) {
		
	}
	
	public static MusicPlayer getInstance() {
		return instance;
	}
	
	private void def(JPanel container) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add((JPanel) this.add(container));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MusicPlayer();
	}
}
