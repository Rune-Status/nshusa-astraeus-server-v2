package io.astraeus.util;

import java.util.concurrent.TimeUnit;

/**
 * A simple class to help manager time.
 * 
 * @author Freyr
 */
public final class Stopwatch {

	/**
	 * The private constructor to prevent instantiation.
	 */
	private Stopwatch() {

	}
	
	public static Stopwatch start() {
		return new Stopwatch();
	}

	/**
	 * The time in nanoseconds.
	 */
	private long time = System.nanoTime();

	/**
	 * The amount of time that has passed since this stopwatch was created.
	 * 
	 * @return The elapsed time in nanoseconds.
	 */
	public long elapsed() {
		return System.nanoTime() - time;
	}

	/**
	 * The amount of time that has passed since this stop watch was created.
	 * 
	 * @param unit
	 *            The desired time unit.
	 *
	 * @return The time in the desired time unit.
	 */
	public long elapsed(TimeUnit unit) {
		if (unit == TimeUnit.NANOSECONDS) {
			throw new IllegalArgumentException("Time is already in nanoseconds!");
		}
		return unit.convert(elapsed(), TimeUnit.NANOSECONDS);
	}

	/**
	 * Resets this stopwatch and sets {@code time} to the current time in
	 * nanoseconds.
	 */
	public Stopwatch reset() {
		time = System.nanoTime();
		return this;
	}

}
