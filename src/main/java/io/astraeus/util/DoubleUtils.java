package io.astraeus.util;

public class DoubleUtils {

	public static double distance(int fx, int fy, int sx, int sy) {
		final int dx = sx - fx;
		final int dy = sy - fy;
		return Math.sqrt(dx * dx + dy * dy);
	}

}
