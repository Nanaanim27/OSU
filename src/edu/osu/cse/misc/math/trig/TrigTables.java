package edu.osu.cse.misc.math.trig;

public class TrigTables {

	private static final double[] SIN, COS, TAN;
	
	static {
		SIN = new double[361];
		COS = new double[361];
		TAN = new double[361];
		
		for (int i = 0; i <= 360; i++) {
			double rad = Math.toRadians(i);
			SIN[i] = Math.sin(rad);
			COS[i] = Math.cos(rad);
			TAN[i] = Math.tan(rad);
		}
	}
	
	public static double sin(int degrees) {
		return SIN[degrees];
	}
	
	public static double sin(double radians) {
		return SIN[(int) Math.toDegrees(radians)];
	}
	
	public static double cos(int degrees) {
		return COS[degrees];
	}
	
	public static double cos(double radians) {
		return COS[(int) Math.toDegrees(radians)];
	}
	public static double tan(int degrees) {
		return TAN[degrees];
	}
	
	public static double tan(double radians) {
		return TAN[(int) Math.toDegrees(radians)];
	}
	
}
