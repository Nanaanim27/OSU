package edu.osu.cse.misc.audio.player.components;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.osu.cse.misc.audio.player.MusicPlayer;
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
		this.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) { 
					System.out.println(getSelectedIndex());
					MusicPlayer.getInstance().updateTab(getSelectedValue());
				}
			}
		});
	}
	
}
