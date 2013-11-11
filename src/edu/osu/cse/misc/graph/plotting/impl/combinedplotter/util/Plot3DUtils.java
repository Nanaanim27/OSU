package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.util;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs.Plot3D;

public class Plot3DUtils {

	public static MouseAdapter cameraControls(final Plot3D plot) {
		return new MouseAdapter() {
			private Point lastPressed;

			@Override
			public void mousePressed(MouseEvent e) {
				this.lastPressed = e.getPoint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = e.getPoint();
				if (this.lastPressed != null) {
					int dx = p.x - this.lastPressed.x;
					int dy = p.y - this.lastPressed.y;

					plot.getCamera().rotationRoll += Math.toRadians(dx);
					//plot.getCamera().y -= Math.toRadians(dy);
					
					this.lastPressed = e.getPoint();
					plot.repaint();
				}
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int dz = (int) Math.signum(-e.getWheelRotation());
				plot.getCamera().setViewingDistance(plot.getCamera().getViewingDistance() + (dz*10));
				plot.repaint();
			}

		};
	}
}
