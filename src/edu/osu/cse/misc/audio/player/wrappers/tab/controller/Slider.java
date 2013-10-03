package edu.osu.cse.misc.audio.player.wrappers.tab.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Slider extends JPanel {

	private double progress = 0D;
	private JPanel filled, empty;
	
	public Slider() {
		
		this.filled = new JPanel();
		this.filled.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.darkGray));
		this.filled.setMaximumSize(new Dimension(2000, 10));
		
		this.empty = new JPanel();
		this.empty.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.darkGray));
		this.empty.setMaximumSize(new Dimension(2000, 10));
		
		this.addMouseListener(this.mouseListener);
		this.addMouseMotionListener(this.mouseListener);

		this.filled.addMouseListener(this.mouseListener);
		this.filled.addMouseMotionListener(this.mouseListener);

		this.empty.addMouseListener(this.mouseListener);
		this.empty.addMouseMotionListener(this.mouseListener);
	}
	
	public void setProgressPercent(double percent) {
		this.progress = percent;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int splitX = (int) (this.getWidth() * this.progress);
		int splitY = (int) (this.getHeight() * this.progress);
		g.setColor(Color.lightGray);
		g.fillOval(splitX - 3, splitY -3, 6, 6);
	}
	
	
	private MouseAdapter mouseListener = new MouseAdapter() {
		
		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			Slider.this.progress = (((double)e.getX()) / Slider.this.getWidth());
		};
		
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = e.getPoint();
			Slider.this.progress = (((double)e.getX()) / Slider.this.getWidth());
		};
	};
	
}
