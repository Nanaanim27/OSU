package edu.osu.cse.misc.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomImage extends JPanel {

	private Dimension dim;
	private BufferedImage image;
	
	public RandomImage(Dimension dim) {
		this.dim = dim;
		this.image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
		this.createImage(image);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.image, 0, 0, null);
		
	}
	
	private void createImage(BufferedImage image) {
		Graphics2D g = image.createGraphics();
		
		Random rand = new Random();
		for (int r  = 0; r < this.dim.height; r++) {
			for (int c = 0; c < this.dim.width; c++) {
				//int monoChromeValue = rand.nextInt(256);
				g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
				g.drawRect(c, r, 1, 1);
			}
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return this.dim;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Random Image");
		frame.setContentPane(new RandomImage(new Dimension(500, 500)));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
