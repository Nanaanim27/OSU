package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.util;

import java.awt.Point;

import edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs.Plot2D;
import edu.osu.cse.misc.graph.plotting.wrappers.graph._2d.Coordinate2D;

public class Plot2DUtils {

	/**
	 * Converts a coordinate in a 2D plane to its relative screen location
	 * 
	 * @param coord A Coordinate2D on the plane
	 * @param plot The Plot2D that holds the coordinate
	 * @return A Point relative to the plot's canvas marking the coordinate's location.
	 */
	public static Point getScreenPoint(Coordinate2D coord, Plot2D plot) {
		float xRatio = (plot.xMax - coord.getX()) / plot.getXAxisLength();
		float yRatio = (plot.yMax - coord.getY()) / plot.getYAxisLength();
		return new Point((int) (plot.getPreferredSize().width * xRatio), 
				(int) (plot.getPreferredSize().height * yRatio));
	}
	
	/**
	 * Determines the real min value a graph can hold given its interval.
	 * 
	 * @param targetValue The desired min value.
	 * @param interval The interval to scale
	 * @return The next value closest to 0 that divides evenly into the given interval;
	 */
	public static float getRealValue(float targetValue, float interval) {
		return targetValue - (targetValue % interval);
	}
}
