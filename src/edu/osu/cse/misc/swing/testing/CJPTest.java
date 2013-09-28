package edu.osu.cse.misc.swing.testing;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.osu.cse.misc.swing.CollapsableJPanel;

public class CJPTest {

	public static void main(String[] args) {
		CollapsableJPanel cjp = new CollapsableJPanel("Playlists");
		JLabel subLabel = new JLabel("Sub Label");
		cjp.addContent(subLabel);
		
		CollapsableJPanel subMenu = new CollapsableJPanel("Favorite Playlist");
		JButton button = new JButton("Press me!");
		subMenu.addContent(button);
		
		cjp.addContent(subMenu);

		
		JFrame mainFrame = new JFrame("CJP Testing");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
		container.add(cjp);
		mainFrame.add(container);
		
		container.setBorder(BorderFactory.createLineBorder(Color.blue));
		cjp.setBorder(BorderFactory.createLineBorder(Color.green));
		
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
}
