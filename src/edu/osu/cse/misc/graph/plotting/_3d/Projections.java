package edu.osu.cse.misc.graph.plotting._3d;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.osu.cse.misc.math.matrices.Matrices;
import edu.osu.cse.misc.math.matrices.Matrix;

public class Projections {
	
	public static final Point3D DEFAULT_CAMERA = new Point3D(100, 100, -100);

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Field");
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				long start = System.currentTimeMillis();
				int count = 0;
				System.out.print("Rendering...");
				for (Point3D p : generateField(20)) {
					Point pt = convert3Dto2D(p, DEFAULT_CAMERA);
					g.drawOval(pt.x, pt.y, 1, 1);
					count++;
				}
				System.out.print("finished " + count + " points after " + (System.currentTimeMillis() - start) + " ms.");
			}
		};
		panel.setPreferredSize(new Dimension(1200, 700));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Point3D p = new Point3D(4, 5, 3);
	}
	
	public static Point convert3Dto2D(Point3D point, Point3D camera) {
		
		Matrix<Double> scaleMatrix = new Matrix<>(new Double[][] { {10D, 0D, 0D}, {0D, 0D, 10D} });
		Matrix<Double> pointMatrix = new Matrix<>(new Double[][] { {point.x}, {point.y}, {point.z} });
		Matrix<Double> offsetMatrix = new Matrix<>(new Double[][] { {0D}, {0D} });
		
		Matrix<Double> scaled = Matrices.multiply(scaleMatrix, pointMatrix);
		Matrix<Double> result = Matrices.add(scaled, offsetMatrix);
		
		return new Point((int) result.getValue(1, 1).doubleValue(), (int) result.getValue(2, 1).doubleValue());
	}
	
	private static Point3D[] generateField(int numTiles) {
		int sideLength = 50;
		
		LinkedList<Point3D> points = new LinkedList<>();
		for (int x = -sideLength/2; x <= sideLength/2; x++) {
			for (int z = -sideLength/2; z <= sideLength/2; z++) {
				points.add(new Point3D(x, 0, z));
			}
		}
		return points.toArray(new Point3D[points.size()]);
	}
	
}
