package edu.osu.cse.misc.projections.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.projections.shape.Line2D;
import edu.osu.cse.misc.projections.shape.Line3D;
import edu.osu.cse.misc.projections.shape.Shape3D;

public class SquareField {

	private static final Point3D camera = new Point3D(300, 300, 800);
	private static final Point3D drawCenter = new Point3D(0, 0, 0);
	
	
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
				int dx = e.getPoint().x - this.pressed.x;
				int dy = e.getPoint().y - this.pressed.y;
				camera.rotateAbout(drawCenter, Math.toRadians(dx), 0, 0);
				this.pressed = e.getPoint();
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
				g.translate(300, 300);
				long start = System.currentTimeMillis();
				for (Shape3D s : shapes) {
					Line2D[] lines = s.convertTo2D().getLines();
					for (int i = 0; i < lines.length; i++) {
						Line2D line = lines[i];
						g.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
						if (i == 0) {
							g.fillOval(line.getEnd().x-2, line.getEnd().y-2, 5, 5);
						}
					}
				}
				System.out.printf("Finished rendering in %d ms.\n", (System.currentTimeMillis()-start));
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
		Point3D left, back, right, front;
		for (int i = 0; i < n; i++) {
			left = new Point3D(drawCenter.x - (i*20), drawCenter.y, drawCenter.z);
			back = new Point3D(drawCenter.x, drawCenter.y, drawCenter.z + (i*20));
			right = new Point3D(drawCenter.x + (i*20), drawCenter.y, drawCenter.z);
			front = new Point3D(drawCenter.x, drawCenter.y, drawCenter.z - (i*20));

			Line3D l1 = new Line3D(left, back), 
					l2 = new Line3D(back, right), 
					l3 = new Line3D(right, front),
					l4 = new Line3D(front, left);
			
			shapes[i] = new Shape3D(camera, l1, l2, l3, l4);
			//System.out.println("Shape: \n" + shapes[i] + "\nProjected:\n" + shapes[i].convertTo2D());
		}
		return shapes;
	}

}
