package edu.osu.cse.misc.math.trig;

public class TrigTables {

	private static final float[] SIN, COS, TAN;
	
	static {
		SIN = new float[721];
		COS = new float[721];
		TAN = new float[721];
		
		for (int i = -360; i <= 360; i++) {
			float rad = (float) Math.toRadians(i);
			SIN[360 + Math.abs(i)] = (float) Math.sin(rad);
			COS[360 + Math.abs(i)] = (float) Math.cos(rad);
			TAN[360 + Math.abs(i)] = (float) Math.tan(rad);
		}
	}
	
	public static float sin(int degrees) {
		degrees %= 360;
		return SIN[360 + Math.abs(degrees)];
	}
	
	public static float sin(float radians) {
		radians %= (Math.PI * 2);
		return SIN[360 + (int) Math.toDegrees(radians)];
	}
	
	public static float cos(int degrees) {
		degrees %= 360;
		return COS[360 + Math.abs(degrees)];
	}
	
	public static float cos(float radians) {
		radians %= (Math.PI * 2);
		return COS[360 + (int) Math.toDegrees(radians)];
	}
	public static float tan(int degrees) {
		degrees %= 360;
		return TAN[360 + Math.abs(degrees)];
	}
	
	public static float tan(float radians) {
		radians %= (Math.PI * 2);
		return TAN[360 + (int) Math.toDegrees(radians)];
	}
	
}
