package edu.osu.cse.misc.audio.player.wrappers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

import edu.osu.cse.misc.audio.player.MusicPlayer;
import edu.osu.cse.misc.audio.player.wrappers.tab.Playlists;

public class Playlist {

	private String name;
	private LinkedList<Song> songs = new LinkedList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public Song[] getSongs() {
		return this.songs.toArray(new Song[this.songs.size()]);
	}
	
	public JLabel getLabel(final Playlists containingInstance) {
		JLabel label = new JLabel(this.name);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MusicPlayer.getInstance().updateTab();
			}
		});
	}
}
