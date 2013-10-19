package edu.osu.cse.misc.projections.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.projections.CoordinateSystem3D;
import edu.osu.cse.misc.projections.shape.Line2D;
import edu.osu.cse.misc.projections.shape.Line3D;
import edu.osu.cse.misc.projections.shape.Shape3D;

public class SquareField {

	private static final CoordinateSystem3D camera = new CoordinateSystem3D(new Point3D(200, 200, 200));
	private static final CoordinateSystem3D field = new CoordinateSystem3D(new Point3D(200, 400, 0));
	private static JPanel p;
	private static MouseAdapter mouse = new MouseAdapter() {
		
		private Point pressed;
		
		@Override
		public void mousePressed(MouseEvent e) {
			pressed = e.getPoint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (pressed != null && e.getPoint() != null) {
				int dx = e.getPoint().x - pressed.x;
				int dy = e.getPoint().y - pressed.y;
				camera.getOrigin().x += dx;
				camera.getOrigin().z += dy;
				pressed = e.getPoint();
				p.repaint();
			}
		};
	};
	
	public static void main(String[] args) {
		
		final Shape3D[] shapes = generateField(10);
		
		JFrame f = new JFrame("Test Field");
		p = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				long start = System.currentTimeMillis();
				for (Shape3D s : shapes) {
					for (Line2D line : s.convertTo2D()) {
						g.drawLine(line.getStart().x, line.getEnd().y, line.getEnd().x, line.getEnd().y);
					}
				}
				System.out.printf("Rendered in %d ms (Camera position: " + camera.getOrigin() + ")\n", System.currentTimeMillis()-start);
			}
			
		};
		p.setPreferredSize(new Dimension(600, 600));
		p.addMouseListener(mouse);
		p.addMouseMotionListener(mouse);
		
		f.add(p);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
	}

	private static Shape3D[] generateField(int n) {
		Shape3D[] shapes = new Shape3D[n];
		Point3D center = field.getOrigin();
		Point3D left, back, right, front;
		for (int i = 0; i < n; i++) {
			left = new Point3D(center.x - (i*20), center.y, center.z);
			back = new Point3D(center.x, center.y, center.z + (i*20));
			right = new Point3D(center.x + (i*20), center.y, center.z);
			front = new Point3D(center.x, center.y, center.z - (i*20));

			Line3D l1 = new Line3D(left, back), 
					l2 = new Line3D(back, right), 
					l3 = new Line3D(right, front),
					l4 = new Line3D(front, left);
			
			shapes[i] = new Shape3D(camera, field, l1, l2, l3, l4);
		}
		return shapes;
	}

}
