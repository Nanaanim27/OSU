package edu.osu.cse.misc.audio.player;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.audio.player.components.Tabs;
import edu.osu.cse.misc.audio.player.components.tabs.Library;
import edu.osu.cse.misc.audio.player.components.tabs.Playlists;
import edu.osu.cse.misc.audio.player.wrappers.Tab;

public class MusicPlayer extends JFrame {

	private static final int WIDTH = 800, HEIGHT = 500;
	
	private static MusicPlayer instance;
	
	public Playlists playlists = new Playlists("Playlists");
	public Library library = new Library("Library");
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private Tab tabContainer;

	private JPanel filler = new JPanel() {{
		setPreferredSize(Tab.PREF_SIZE);
	}};
	
	public MusicPlayer() {
		instance = this;
		JPanel container = new JPanel(new GridBagLayout());
		this.gbc.anchor = GridBagConstraints.NORTHWEST;
		this.gbc.fill = GridBagConstraints.NONE;
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.weightx = 1D;
		this.gbc.weighty = 1D;
		
		container.add(new Tabs(), this.gbc);
		def(container);
		this.updateTab(null);
	}
	
	public static MusicPlayer getInstance() {
		return instance;
	}
	
	/**
	 * Updates the container to set the central pane to the respective Tab's content.
	 * 
	 * @param selectedTab The tab to set the central pane to.
	 */
	public void updateTab(Tab selectedTab) {
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		if (this.tabContainer != null) {
			this.getContentPane().remove(this.tabContainer);
		}
		this.getContentPane().remove(this.filler);
		if (selectedTab == null) {
			this.getContentPane().add(this.filler);
		}
		else {
			this.tabContainer = selectedTab;
			this.getContentPane().add(selectedTab);
		}
		System.out.println("this.count: " + this.getContentPane().getComponentCount());
		this.getContentPane().repaint();
		this.repaint();
		this.pack();
	}
	
	private void def(JPanel container) {
		this.setContentPane((JPanel) this.add(container));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MusicPlayer();
	}
}
