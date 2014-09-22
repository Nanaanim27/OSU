package edu.osu.cse.misc.projections.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.projections.camera.Camera;
import edu.osu.cse.misc.projections.shape.Line2D;
import edu.osu.cse.misc.projections.shape.Shape2D;

public class SquareField {

	private static final Camera camera = new Camera(300, 300, 1200);
	private static final Point3D drawCenter = new Point3D(0, 0, 0);
	private static Shape2D[] shapes = generateField(30);
	
	
	private static JPanel p;
	private static MouseAdapter mouse = new MouseAdapter() {
		
		private Point pressed;
		
		@Override
		public void mousePressed(MouseEvent e) {
			this.pressed = e.getPoint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (this.pressed != null && e.getPoint() != null) {
				int dx = -(e.getPoint().x - this.pressed.x);
				int dy = e.getPoint().y - this.pressed.y;
				
				camera.rotationPitch += (dx / 50D);
				camera.rotationYaw += (dy / 50D);
				
				shapes = generateField(30);
				this.pressed = e.getPoint();
				p.repaint();
			}
		};
	};
	
	private static KeyAdapter keyboard = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			System.out.println(keyCode);
			if (keyCode == KeyEvent.VK_UP) {
				camera.z -= 50f;
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				camera.z += 50f;
			}
			if (camera.z < 0f) camera.z = 0f;
			shapes = generateField(30);
			p.repaint();
		};
	};
	
	public static void main(String[] args) {
		
		
		JFrame f = new JFrame("Test Field");
		p = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.translate(300, 300);
				
				System.out.println("Current rotation: " + (camera.rotationYaw / Math.PI) + " pi.");
				
				for (Shape2D s : shapes) {
					for (Line2D l : s.getLines()) {
						g.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
					}
				}
			}
			
		};
		p.setPreferredSize(new Dimension(900, 900));
		p.setFocusable(true);
		p.addMouseListener(mouse);
		p.addMouseMotionListener(mouse);
		p.addKeyListener(keyboard);
		
		f.add(p);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
	}

	private static Shape2D[] generateField(int n) {
		Shape2D[] shapes = new Shape2D[n];
		Point3D left, back, right, front;
		Point _left, _back, _right, _front;
		
		final int GAP = 25;
		for (int i = 0; i < n; i++) {
			left = new Point3D(drawCenter.x - (i*GAP), drawCenter.y, drawCenter.z);
			back = new Point3D(drawCenter.x, drawCenter.y, drawCenter.z + (i*GAP));
			right = new Point3D(drawCenter.x + (i*GAP), drawCenter.y, drawCenter.z);
			front = new Point3D(drawCenter.x, drawCenter.y, drawCenter.z - (i*GAP));
			
			_left = camera.project(left);
			_back = camera.project(back);
			_right = camera.project(right);
			_front = camera.project(front);
			
			
			Line2D l1 = new Line2D(_left, _back), 
					l2 = new Line2D(_back, _right), 
					l3 = new Line2D(_right, _front),
					l4 = new Line2D(_front, _left);
			
			shapes[i] = new Shape2D(l1, l2, l3, l4);
		}
		return shapes;
	}

}
