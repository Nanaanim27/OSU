package edu.osu.cse.misc.audio.player.wrappers;

import java.io.Serializable;
import java.util.LinkedList;

public class Playlist extends Tab implements Serializable {

	public Playlist(String label) {
		super(label);
	}

	private LinkedList<Song> songs = new LinkedList<>();
	
	public boolean addSong(Song song) {
		return this.songs.add(song);
	}
	
	public boolean removeSong(Song song) {
		return this.songs.remove(song);
	}
	
}
