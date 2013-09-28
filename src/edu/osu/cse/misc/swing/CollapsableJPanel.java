package edu.osu.cse.misc.swing;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CollapsableJPanel extends JPanel {

	private JPanel header = new JPanel(new GridBagLayout());
	private GridBagConstraints headerGbc = new GridBagConstraints() {{
		this.weightx = 0D;
		this.weighty = 0D;
		this.anchor = GridBagConstraints.WEST;
		this.fill = GridBagConstraints.NONE;
	}};

	private JPanel contents = new JPanel(new GridBagLayout());
	private GridBagConstraints contentsGbc = new GridBagConstraints() {{
		this.weightx = 0D;
		this.weighty = 0D;
		this.anchor = GridBagConstraints.WEST;
		this.fill = GridBagConstraints.NONE;
	}};

	private JLabel headerLabel, expandIcon, collapseIcon;
	private boolean isCollapsed = false;

	public CollapsableJPanel(String label) {
		try {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			initHeader(label);
			this.add(this.header);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initHeader(String label) throws IOException {
		this.headerLabel = new JLabel(label);
		this.headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 2, 0));
		this.expandIcon = new JLabel(new ImageIcon(ImageIO.read(CollapsableJPanel.class.getResourceAsStream("/edu/osu/cse/misc/swing/Expand.png"))), SwingConstants.LEFT);
		this.collapseIcon = new JLabel(new ImageIcon(ImageIO.read(CollapsableJPanel.class.getResourceAsStream("/edu/osu/cse/misc/swing/Collapse.png"))), SwingConstants.LEFT);
		
		this.headerGbc.gridx = 0; this.headerGbc.gridy = 0;
		this.expandIcon.setVisible(false);
		this.header.add(this.expandIcon, this.headerGbc);
		this.header.add(this.collapseIcon, this.headerGbc);
		
		this.headerGbc.gridx = 1;
		this.header.add(this.headerLabel, this.headerGbc);
		this.header.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.header.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CollapsableJPanel.this.setCollapsed(!CollapsableJPanel.this.isCollapsed);
			}
		});
	}

	public void setCollapsed(boolean collapsed) {
		this.isCollapsed = collapsed;
		this.collapseIcon.setVisible(!collapsed);
		this.expandIcon.setVisible(collapsed);
	}

	
	public void addContent(JComponent c) {
		System.out.println("New component x: " + 0);
		System.out.println("New component y: " + this.contents.getComponentCount());
		addComponent(new CollapsableComponent(c, 0, this.contents.getComponentCount()));
	}
	
	private void addComponent(CollapsableComponent c) {
		for (Component comp : this.contents.getComponents()) {
			CollapsableComponent ccomp = (CollapsableComponent) comp;
			if (ccomp.GRID_X == c.GRID_X && ccomp.GRID_Y == c.GRID_Y) {
				this.contents.remove(ccomp);
			}
		}
		this.contentsGbc.gridx = c.GRID_X;
		this.contentsGbc.gridy = c.GRID_Y;
		this.contents.add(c, this.contentsGbc);
	}

	private static class CollapsableComponent extends JComponent {

		private final int GRID_X, GRID_Y;

		public CollapsableComponent(JComponent parent, int gridx, int gridy) {
			super();
			this.GRID_X = gridx;
			this.GRID_Y = gridy;
		}
	}

}
