package io.astraeus.game.world.entity;

import java.util.Objects;

import lombok.Value;

/**
 * Represents a single graphic that can be used by entities. Also known as GFX.
 * 
 * @author Freyr
 */
@Value
public final class Graphic {

	public static final Graphic RESET = Graphic.create(-1);

	public static final int HIGH_HEIGHT = 100;

	public static final int MEDIUM_HEIGHT = 10;

	public static final int LOW_HEIGHT = 1;

	/**
	 * The id of this graphic.
	 */
	private final int id;

	/**
	 * The delay of this graphic.
	 */
	private final int delay;

	/**
	 * The height of this graphic.
	 */
	private final int height;

	/**
	 * Creates a new {@link Graphic}.
	 * 
	 * @param id
	 *            The id for this graphic.
	 * 
	 * @param delay
	 *            The delay for this graphic.
	 *
	 * @param high
	 *            The flag that denotes this is a high graphic.
	 */
	private Graphic(int id, int delay, int height) {
		this.id = id;
		this.delay = delay;
		this.height = height;
	}

	public static Graphic create(int id, int delay, int height) {
		return new Graphic(id, delay, height);
	}

	public static Graphic create(int id, int delay) {
		return new Graphic(id, delay, 0);
	}

	public static Graphic create(int id) {
		return new Graphic(id, 0, 0);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, height);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o instanceof Graphic) {
			Graphic g = (Graphic) o;

			return hashCode() == g.hashCode();
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("GRAPHIC[id=%s, delay=%s, height=]", id, delay, height);
	}

}
