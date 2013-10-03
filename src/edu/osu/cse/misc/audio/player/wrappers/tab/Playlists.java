package edu.osu.cse.misc.audio.player.wrappers.tab;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.interfaces.Panel;
import edu.osu.cse.misc.audio.player.wrappers.Playlist;
import edu.osu.cse.misc.swing.CollapsableJPanel;

public class Playlists implements Panel {

	public JLabel selectedPlaylist;
	
	public Playlists() {
		
	}
	
	@Override
	public JPanel getPanel() {
		CollapsableJPanel panel = new CollapsableJPanel("Playlists");
		
		return panel;
	}
	
	public void addPlaylist(Playlist pl) {
		JLabel comp = pl.getLabel(this);
	}
	
	
}
