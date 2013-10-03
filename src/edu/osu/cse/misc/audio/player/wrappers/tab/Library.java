package edu.osu.cse.misc.audio.player.wrappers.tab;

import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.interfaces.Panel;
import edu.osu.cse.misc.swing.CollapsableJPanel;

public class Library implements Panel {

	@Override
	public JPanel getPanel() {
		CollapsableJPanel panel = new CollapsableJPanel("Library");
		
		CollapsableJPanel songs = new CollapsableJPanel("Songs");
		CollapsableJPanel artists = new CollapsableJPanel("Artists");
		
		panel.addContent(songs);
		panel.addContent(artists);
		
		return panel;
	}



}
