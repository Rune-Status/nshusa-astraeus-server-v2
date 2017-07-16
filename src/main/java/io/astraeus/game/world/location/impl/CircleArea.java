package io.astraeus.game.world.location.impl;

import io.astraeus.game.world.Position;
import io.astraeus.game.world.location.Area;
import lombok.Getter;

/**
 * The location type that models any area in a circle or oval shape.
 *
 * @author lare96 <http://github.com/lare96>
 */
public class CircleArea extends Area {

  /**
   * The name of this area.
   */
  @Getter private final String name;

  /**
   * The center {@code X} coordinate.
   */
  @Getter private final int x;

  /**
   * The center {@code Y} coordinate.
   */
  @Getter private final int y;

  /**
   * The center {@code Height} coordinate.
   */
  @Getter private final int height;

  /**
   * The radius of this area.
   */
  @Getter private final int radius;

  /**
   * Creates a new {@link CircleLocation}.
   *
   * @param x the center {@code X} coordinate.
   * @param y the center {@code Y} coordinate.
   * @param z the center {@code Height} coordinate.
   * @param radius the radius of this location from the center coordinates.
   */
  public CircleArea(String name, int x, int y, int radius) {
    this(name, x, y, 0, radius);
  }

  /**
   * Creates a new {@link CircleLocation}.
   * 
   * @param name The name of this area.
   *
   * @param x the center {@code X} coordinate.
   * @param y the center {@code Y} coordinate.
   * 
   * @param radius the radius of this location from the center coordinates.
   */
  public CircleArea(int x, int y, int height, int radius) {
    this("", x, y, height, radius);
  }

  /**
   * Creates a new {@link CircleLocation}.
   * 
   * @param name The name of this area.
   *
   * @param x the center {@code X} coordinate.
   * @param y the center {@code Y} coordinate.
   * @param z the center {@code Height} coordinate.
   * @param radius the radius of this location from the center coordinates.
   */
  public CircleArea(String name, int x, int y, int height, int radius) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.height = height;
    this.radius = radius;
  }

  @Override
  public boolean inArea(Position location) {
    if (location.getHeight() != height) {
      return false;
    }
    return Math.pow((location.getX() - x), 2) + Math.pow((location.getY() - y), 2) <= Math
        .pow(radius, 2);
  }

}
