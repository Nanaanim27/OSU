package edu.osu.cse.misc.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Resources {

	private static final String root = "/edu/osu/cse/misc/resources";

	public enum Resource {
		FX("fx.png");

	private String path;
	private BufferedImage image;

	Resource(String path) {
		this.path = path;
		this.image = this.readImage();
	}

	public BufferedImage getImage() {
		return this.image;
	}

	private BufferedImage readImage() {
		try {
			return ImageIO.read(Resource.class.getResourceAsStream(this.getPath()));
		} catch (final Exception ignored) {
			System.err.println("Can't read image: " + this.path);
			return null;
		}
	}

	public String getPath() {
		return root + "/" + this.path;
	}

	public InputStream getInputStream() {
		return Resource.class.getResourceAsStream(this.getPath());
	}
}
}
