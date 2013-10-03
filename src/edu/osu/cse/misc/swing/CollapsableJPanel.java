package edu.osu.cse.misc.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.osu.cse.misc.swing.testing.Debug;

public class CollapsableJPanel extends JPanel {

	private int indent;

	private GridBagConstraints mainGbc = new GridBagConstraints() {{
		this.anchor = GridBagConstraints.NORTHWEST;
		this.fill = GridBagConstraints.NONE;
		this.weightx = 0D;
		this.weighty = 0D;
		this.gridx = 0;
		this.gridy = 0;
	}};

	private JPanel header = new JPanel(new GridBagLayout());
	private GridBagConstraints headerGbc = new GridBagConstraints() {{
		this.weightx = 0D;
		this.weighty = 0D;
		this.anchor = GridBagConstraints.NORTHWEST;
		this.fill = GridBagConstraints.NONE;
	}};

	private JPanel contents = new JPanel(new GridBagLayout());
	private GridBagConstraints contentsGbc = new GridBagConstraints() {{
		this.weightx = 0D;
		this.weighty = 0D;
		this.anchor = GridBagConstraints.NORTHWEST;
		this.fill = GridBagConstraints.NONE;
	}};

	private JLabel headerLabel, expandIcon, collapseIcon;
	private boolean isCollapsed = false;

	public CollapsableJPanel(String label) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		this.add(this.initHeader(label));
		this.mainGbc.gridy++;
		this.setCollapsed(false);
	}
	
	private JPanel initHeader(String label) {
		try {
			this.headerLabel = new JLabel(label);
			this.headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 2, 0));
			this.expandIcon = new JLabel(new ImageIcon(ImageIO.read(CollapsableJPanel.class.getResourceAsStream("/edu/osu/cse/misc/swing/resources/Expand.png"))), SwingConstants.LEFT);
			this.collapseIcon = new JLabel(new ImageIcon(ImageIO.read(CollapsableJPanel.class.getResourceAsStream("/edu/osu/cse/misc/swing/resources/Collapse.png"))), SwingConstants.LEFT);

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

			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
			panel.add(Box.createHorizontalStrut(this.indent * 5));
			panel.add(this.header);
			return panel;
		} catch (IOException nil) {
			return null;
		}
	}

	/**
	 * Toggles the collapse state of this component.
	 * 
	 * @param collapsed This panel is collapsed if #collapsed is <tt>true</tt>
	 */
	public void setCollapsed(boolean collapsed) {
		this.isCollapsed = collapsed;
		this.collapseIcon.setVisible(!collapsed);
		this.expandIcon.setVisible(collapsed);

		if (collapsed && Arrays.asList(this.getComponents()).contains(this.contents))
			this.remove(this.contents);
		else if (!collapsed && !Arrays.asList(this.getComponents()).contains(this.contents))
			this.add(this.contents);

		JFrame frameParent = this.getParentOfType(JFrame.class);
		if (frameParent != null) {
			Dimension pref = frameParent.getPreferredSize();
			Dimension cur = frameParent.getSize();
			if (frameParent != null && ((pref.width > cur.width) || (pref.height > cur.height)))
				frameParent.pack();
		}
		this.repaint();
	}

	private <T extends Container> T getParentOfType(Class<T> type) {
		Container c = this.getParent();
		while (c != null && c.getClass() != type) {
			c = c.getParent();
		}
		return (T) c;
	}

	public void addContent(JComponent c) {
		System.out.println("Adding: " + c);
		addComponent(new CollapsableComponent(c, 0, this.contents.getComponentCount()));
	}

	private void addComponent(CollapsableComponent c) {
		for (Component comp : this.contents.getComponents()) {
			System.out.println(comp.getClass().getSimpleName());
			CollapsableComponent ccomp = CollapsableComponent.pointers.get(comp);
			if (ccomp != null) {
				if (ccomp.GRID_X == c.GRID_X && ccomp.GRID_Y == c.GRID_Y) {
					this.contents.remove(ccomp.getComponent());
				}
			}
		}
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(Box.createHorizontalStrut(this.getX() + 5));
		panel.add(c.getComponent());
		this.contentsGbc.gridy = c.GRID_Y;
		this.contents.add(panel, this.contentsGbc);
	}

	private static class CollapsableComponent {

		private static HashMap<JComponent, CollapsableComponent> pointers = new HashMap<>();

		private final int GRID_X, GRID_Y;
		private JComponent parent;

		private CollapsableComponent(JComponent parent, int gridx, int gridy) {
			super();
			parent.setBorder(Debug.lineBorder(Color.green));
			System.out.println("New CC");
			this.GRID_X = gridx;
			this.GRID_Y = gridy;
			this.parent = parent;
			CollapsableComponent.pointers.put(this.parent, this);
		}

		private JComponent getComponent() {
			return this.parent;
		}
	}

}
