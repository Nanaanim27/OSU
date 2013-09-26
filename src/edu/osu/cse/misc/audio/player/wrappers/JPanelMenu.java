package edu.osu.cse.misc.audio.player.wrappers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

public class JPanelMenu extends JPanel {

	private LinkedList<JPanel> panels;
	
	public JPanelMenu(JPanel...panels) {
		this.panels = new LinkedList<>();
		for (JPanel panelMenu : panels) {
			this.panels.add(panelMenu);
		}
	}
	
	public JPanel build() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.weightx = 1D;
		gbc.weighty = 0D;
		
		build(this, panel, gbc, 0, 0);
		return panel;
	}
	
	private void build(JPanelMenu root, JPanel mainPanel, GridBagConstraints gbc, int x, int y) {
		
		
		
		for (JPanel panel : root.panels) {
			if (panel instanceof JPanelMenu) {
				build((JPanelMenu) panel, mainPanel, gbc, x, y);
			}
			else {
				
			}
		}
	}
	
	public void addPanel(JPanel panel) {
		this.panels.add(panel);
	}
	
	public void removePanel(JPanel panel) {
		this.panels.remove(panel);
	}
	
}
