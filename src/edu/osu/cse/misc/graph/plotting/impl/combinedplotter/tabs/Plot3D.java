package edu.osu.cse.misc.graph.plotting.impl.combinedplotter.tabs;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import edu.osu.cse.misc.graph.plotting._3d.Point3D;
import edu.osu.cse.misc.graph.plotting.impl.combinedplotter.util.Plot2DUtils;
import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;
import edu.osu.cse.misc.projections.camera.Camera;

/**
 * A canvas that supports the drawing of Three-Dimensional functions
 */
public class Plot3D extends Canvas {

	public final float xMin, xMax, yMin, yMax, zMin, zMax;
	private final float xMinReal, xMaxReal, yMinReal, yMaxReal, zMinReal, zMaxReal;
	
	public final float xInterval, yInterval, zInterval;
	
	private LinkedList<AbstractFunction3D> functions = new LinkedList<>();
	private Camera camera = new Camera(0f, 5f, 10f) {{
		this.rotationYaw = (float) Math.PI/4;
	}};
	
	
	public Plot3D(float xMin, float xMax, float yMin, float yMax, float zMin,
			float zMax, float xInterval, float yInterval, float zInterval) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
		
		this.xInterval = xInterval;
		this.yInterval = yInterval;
		this.zInterval = zInterval;
		
		this.xMinReal = Plot2DUtils.getRealValue(xMin, xInterval);
		this.xMaxReal = Plot2DUtils.getRealValue(xMax, xInterval);
		this.yMinReal = Plot2DUtils.getRealValue(yMin, yInterval);
		this.yMaxReal = Plot2DUtils.getRealValue(yMax, yInterval);
		this.zMinReal = Plot2DUtils.getRealValue(zMin, zInterval);
		this.zMaxReal = Plot2DUtils.getRealValue(zMax, zInterval);
	}

	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		
		drawGrid(g);
	}
	
	private void drawGrid(Graphics2D g) {
		Point3D xMax = new Point3D(this.xMaxReal, 0f, 0f), xMin = new Point3D(this.xMinReal, 0f, 0f);
		Point3D yMax = new Point3D(0f, this.yMaxReal, 0f), yMin = new Point3D(0f, this.yMinReal, 0f);
		Point3D zMax = new Point3D(0f, 0f, this.zMaxReal), zMin = new Point3D(0f, 0f, this.zMinReal);
		
		Point _xMax = this.camera.project(xMax), _xMin = this.camera.project(xMin);
		Point _yMax = this.camera.project(yMax), _yMin = this.camera.project(yMin);
		Point _zMax = this.camera.project(zMax), _zMin = this.camera.project(zMin);
		
		g.drawLine(_xMin.x, _xMin.y, _xMax.x, _xMax.y);
		g.drawLine(_yMin.x, _yMin.y, _yMax.x, _yMax.y);
		g.drawLine(_zMin.x, _zMin.y, _zMax.x, _zMax.y);
	}
	
	public boolean add(AbstractFunction3D function) {
		return this.functions.add(function);
	}
	
	public boolean remove(AbstractFunction3D function) {
		return this.functions.remove(function);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
	
}
