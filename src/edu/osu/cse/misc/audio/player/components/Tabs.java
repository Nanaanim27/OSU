package edu.osu.cse.misc.audio.player.components;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import edu.osu.cse.misc.audio.player.components.tabs.Library;
import edu.osu.cse.misc.audio.player.components.tabs.Playlists;
import edu.osu.cse.misc.audio.player.wrappers.Tab;

public class Tabs extends JList<Tab> {

	private DefaultListModel<Tab> model = new DefaultListModel<Tab>() {{
		addElement(new Library("Library"));
		addElement(new Playlists("Playlists"));
	}};
	
	public Tabs() {
		this.setModel(this.model);
	}
	
}
