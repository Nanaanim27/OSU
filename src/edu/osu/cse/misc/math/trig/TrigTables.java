package edu.osu.cse.misc.math.trig;

public class TrigTables {

	private static final double[] SIN, COS, TAN;
	
	static {
		SIN = new double[721];
		COS = new double[721];
		TAN = new double[721];
		
		for (int i = -360; i <= 360; i++) {
			double rad = Math.toRadians(i);
			SIN[360 + Math.abs(i)] = Math.sin(rad);
			COS[360 + Math.abs(i)] = Math.cos(rad);
			TAN[360 + Math.abs(i)] = Math.tan(rad);
		}
	}
	
	public static double sin(int degrees) {
		return SIN[360 + Math.abs(degrees)];
	}
	
	public static double sin(double radians) {
		return SIN[360 + (int) Math.toDegrees(radians)];
	}
	
	public static double cos(int degrees) {
		return COS[360 + Math.abs(degrees)];
	}
	
	public static double cos(double radians) {
		return COS[360 + (int) Math.toDegrees(radians)];
	}
	public static double tan(int degrees) {
		return TAN[360 + Math.abs(degrees)];
	}
	
	public static double tan(double radians) {
		return TAN[360 + (int) Math.toDegrees(radians)];
	}
	
}
