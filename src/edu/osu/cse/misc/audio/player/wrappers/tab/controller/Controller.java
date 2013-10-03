package edu.osu.cse.misc.audio.player.wrappers.tab.controller;

import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.MusicPlayer;

public class Controller extends JPanel {

	private MusicPlayer instance;
	
	public Controller(MusicPlayer instance) {
		this.instance = instance;
		this.add(new Slider());
	}
	
	public MusicPlayer getPlayer() {
		return this.instance;
	}
	
}
