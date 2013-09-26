package edu.osu.cse.misc.audio.player.components.tabs;

import edu.osu.cse.misc.audio.player.wrappers.Playlist;
import edu.osu.cse.misc.audio.player.wrappers.Tab;

public class Playlists extends Tab {

	public Playlists(String label) {
		super(label);
	}
	
	public boolean addPlaylist(Playlist list) {
		return this.addTab(list);
	}

}
