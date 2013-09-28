package edu.osu.cse.misc.swing.testing;

import javax.swing.JLabel;

import edu.osu.cse.misc.swing.CollapsableJPanel;

public class CJPTest {

	public static void main(String[] args) {
		CollapsableJPanel cjp = new CollapsableJPanel("Playlists");
		JLabel subLabel = new JLabel("Sub Label");
		cjp.addContent(subLabel);
		new DefaultFrame(cjp);
	}
	
}
