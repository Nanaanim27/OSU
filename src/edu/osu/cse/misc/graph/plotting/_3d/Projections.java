package edu.osu.cse.misc.graph.plotting._3d;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;

public class Projections {

	public static final Point3D CENTER = new Point3D(0, 0, 0);
	public static final Point3D DEFAULT_CAMERA = new Point3D(300, 300, -2000);

	private static double xRot = 0D, yRot = 0D, zRot = 0D;
	
	private static Double[][] xData = {
		{ 1D, 0D, 0D },
		{ 0D, Math.cos(xRot), -Math.sin(xRot) },
		{ 0D, Math.sin(xRot), Math.cos(xRot) }
	}, yData = {
		{ Math.cos(yRot), 0D, Math.sin(yRot) },
		{ 0D, 1D, 0D},
		{ -Math.sin(yRot), 0D, Math.cos(yRot) }
	}, zData = {
		{ Math.cos(zRot), -Math.sin(zRot), 0D },
		{ Math.sin(zRot), Math.cos(zRot), 0D},
		{ 0D, 0D, 1D }
	};
	
	public static final Matrix<Double> xRotation = new Matrix<>(xData);
	public static final Matrix<Double> yRotation = new Matrix<>(yData);
	public static final Matrix<Double> zRotation = new Matrix<>(zData);
	
	private static JPanel panel = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Point xMin, xMax, yMin, yMax, zMin, zMax;
			
			xMin = convert3Dto2D(new Point3D(0, 0, 0), DEFAULT_CAMERA);
			xMax = convert3Dto2D(new Point3D(panel.getWidth(), 0, 0), DEFAULT_CAMERA);
			
			yMin = convert3Dto2D(new Point3D(0, 0, 0), DEFAULT_CAMERA);
			yMax = convert3Dto2D(new Point3D(0, panel.getHeight(), 0), DEFAULT_CAMERA);
			
			zMin = convert3Dto2D(new Point3D(0, 0, -1000), DEFAULT_CAMERA);
			zMax = convert3Dto2D(new Point3D(0, 0, 1000), DEFAULT_CAMERA);
			
			Point center = convert3Dto2D(CENTER, DEFAULT_CAMERA);
			g.fillOval(center.x-4, center.y-4, 8, 8);
			
			g.drawString("x", xMax.x, xMax.y);
			g.drawString("y", yMax.x, yMax.y);
			g.drawString("z", zMax.x, zMax.y);
			
			g.drawLine(xMin.x, xMin.y, xMax.x, xMax.y);
			g.drawLine(yMin.x, yMin.y, yMax.x, yMax.y);
			g.drawLine(zMin.x, zMin.y, zMax.x, zMax.y);
			
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
		
		g.drawOval(front2.x-2, front2.y-2, 4, 4);
		g.drawLine(front2.x, front2.y, right2.x, right2.y);
		g.drawLine(right2.x, right2.y, back2.x, back2.y);
		g.drawLine(back2.x, back2.y, left2.x, left2.y);
		g.drawLine(left2.x, left2.y, front2.x, front2.y);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Field");

		panel.addMouseListener(list);
		panel.addMouseMotionListener(list);
		panel.addMouseWheelListener(list);
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
				xRot += Math.toRadians(dX % 360D / 100);
				yRot += Math.toRadians(dY % 360D / 100);
				
				xData = new Double[][] {{ 1D, 0D, 0D },
						{ 0D, Math.cos(xRot), -Math.sin(xRot) },
						{ 0D, Math.sin(xRot), Math.cos(xRot) }};
				
				yData = new Double[][] {
						{ Math.cos(yRot), 0D, Math.sin(yRot) },
						{ 0D, 1D, 0D},
						{ -Math.sin(yRot), 0D, Math.cos(yRot) }};
				
				xRotation.setData(xData);
				yRotation.setData(yData);
				
				panel.repaint();
				this.lastPress = e.getPoint();
			}
		}
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			DEFAULT_CAMERA.z -= e.getWheelRotation()*30;
			panel.repaint();
		}
	};

	public static Point convert3Dto2D(Point3D point, Point3D camera) {
		
		Matrix<Double> cameraM = Matrices.convertFromPoint3D(camera);
		Matrix<Double> pointM = Matrices.convertFromPoint3D(point);
		
		Matrix<Double> m4 = Matrices.subtract(pointM, cameraM);
		
		Matrix<Double> dMatrix = Matrices.multiply(xRotation, Matrices.multiply(yRotation, zRotation));
		System.out.println("dMatrix:\n" + dMatrix);
		Point3D dPoint = Matrices.convertToPoint3D(Matrices.multiply(dMatrix, m4));
		
		double bX = (camera.z / dPoint.z) * (dPoint.x - camera.x);
		double bY = (camera.z / dPoint.z) * (dPoint.y - camera.y);
		
		return new Point((int) bX, (int) bY);
	}

}
