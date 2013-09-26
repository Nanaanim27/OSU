package edu.osu.cse.misc.audio.player.wrappers.tab;

import java.util.LinkedList;

import edu.osu.cse.misc.audio.player.wrappers.JPanelMenu;

public abstract class AbstractTab extends JPanelMenu {

	protected LinkedList<AbstractTab> subTabs = new LinkedList<>();
	
	public abstract void build();
	
}
