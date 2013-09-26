package edu.osu.cse.misc.audio.player.wrappers;

import java.awt.Dimension;
import java.beans.Transient;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * A Tab to be displayed on the left-portion of the MusicPlayer.
 * <br />Can hold nested tabs as such:<pre>
 * Library
 * Favorites
 * Playlists
 * -Playlist1
 * --Song1
 * --Song2
 * --Song3
 * -Playlist2
 * --Song1
 * --Song2
 * </pre>
 */
public class Tab extends JPanel {

	private String label;
	private LinkedList<Tab> subTabs;
	
	public static final Dimension PREF_SIZE = new Dimension(600, 400);
	
	public Tab(String label) {
		this.label = label;
	}
	
	protected boolean addTab(Tab tab) {
		if (this.subTabs == null) {
			this.subTabs = new LinkedList<>();
		}
		boolean b = this.subTabs.add(tab);
		this.repaint();
		return b;
	}
	
	protected boolean removeTab(Tab tab) {
		boolean b = this.subTabs == null || this.subTabs.remove(tab);
		this.repaint();
		return b;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		return PREF_SIZE;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
	
}
