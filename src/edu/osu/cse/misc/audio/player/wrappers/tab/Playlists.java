package edu.osu.cse.misc.audio.player.wrappers.tab;

import javax.swing.JLabel;

import edu.osu.cse.misc.audio.player.wrappers.Playlist;
import edu.osu.cse.misc.swing.CollapsableJPanel;

public class Playlists extends CollapsableJPanel {

	public JLabel selectedPlaylist;
	
	public Playlists() {
		super("Playlists");
	}
	
	public void addPlaylist(Playlist pl) {
		JLabel comp = pl.getLabel(this);
	}
	
	
}
