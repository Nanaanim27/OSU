package edu.osu.cse.misc.graph.plotting._3d;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;

public class Projections {

	public static final Point3D DEFAULT_CAMERA = new Point3D(600, 300, -500);

	private static JPanel panel = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			long start = System.currentTimeMillis();
			
			Point3D center = new Point3D(0, 0, 0);
			Point center2D = convert3Dto2D(center, DEFAULT_CAMERA);
			
			g.fillOval(center2D.x, center2D.y, 4, 4);
			
			drawSquare((Graphics2D) g, 100);
			drawSquare((Graphics2D) g, 200);
		}
	};
	
	private static void drawSquare(Graphics2D g, int len) {
		Point3D front, back, left, right;
		front = new Point3D(0, 0, len);
		back = new Point3D(0, 0, -len);
		left = new Point3D(-len, 0, 0);
		right = new Point3D(len, 0, 0);

		Point front2, back2, left2, right2;
		front2 = convert3Dto2D(front, DEFAULT_CAMERA);
		back2 = convert3Dto2D(back, DEFAULT_CAMERA);
		left2 = convert3Dto2D(left, DEFAULT_CAMERA);
		right2 = convert3Dto2D(right, DEFAULT_CAMERA);
		
		
		g.drawLine(front2.x, front2.y, right2.x, right2.y);
		g.drawLine(right2.x, right2.y, back2.x, back2.y);
		g.drawLine(back2.x, back2.y, left2.x, left2.y);
		g.drawLine(left2.x, left2.y, front2.x, front2.y);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Field");

		panel.addMouseListener(list);
		panel.addMouseMotionListener(list);
		panel.setPreferredSize(new Dimension(1200, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Point3D p = new Point3D(4, 5, 3);
	}

	private static MouseAdapter list = new MouseAdapter() {
		private Point lastPress;

		@Override
		public void mousePressed(MouseEvent e) {
			this.lastPress = e.getPoint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				int dX = e.getX() - this.lastPress.x;
				int dY = e.getY() - this.lastPress.y;
				DEFAULT_CAMERA.x += dX;
				DEFAULT_CAMERA.y += dY;
				panel.repaint();
				this.lastPress = e.getPoint();
			}
		}
	};

	public static Point convert3Dto2D(Point3D point, Point3D camera) {
		Matrix<Double> m1 = new Matrix<>(new Double[][] {
				{ 1D, 0D, 0D },
				{ 0D, Math.cos(0D), -Math.sin(0D) },
				{ 0D, Math.sin(0D), Math.cos(0D) }
		});

		Matrix<Double> m2 = new Matrix<>(new Double[][] {
				{ Math.cos(0D), 0D, Math.sin(0D) },
				{ 0D, 1D, 0D},
				{ -Math.sin(0D), 0D, Math.cos(0D) }
		});
		
		Matrix<Double> m3 = new Matrix<>(new Double[][] {
				{ Math.cos(0D), -Math.sin(0D), 0D },
				{ Math.sin(0D), Math.cos(0D), 0D},
				{ 0D, 0D, 1D }
		});
		
		Matrix<Double> cameraM = Matrices.convertFromPoint3D(camera);
		Matrix<Double> pointM = Matrices.convertFromPoint3D(point);
		
		Matrix<Double> m4 = Matrices.subtract(pointM, cameraM);
		
		Matrix<Double> dMatrix = Matrices.multiply(m1, Matrices.multiply(m2, Matrices.multiply(m3, m4)));
		Point3D dPoint = Matrices.convertToPoint3D(dMatrix);
		
		double bX = (camera.z / dPoint.z) * (dPoint.x - camera.x);
		double bY = (camera.z / dPoint.z) * (dPoint.y - camera.y);
		
		return new Point((int) bX, (int) bY);
		
	}

}
