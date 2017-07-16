package io.astraeus.game.world.location.impl;

import io.astraeus.game.world.Position;
import io.astraeus.game.world.location.Area;
import lombok.Getter;

/**
 * The location type that models any area in a square or rectangle shape.
 *
 * @author lare96 <http://github.com/lare96>
 */
public final class SquareArea extends Area {

  /**
   * The name of this area.
   */
  @Getter private final String name;

  /**
   * The south-west {@code X} corner of the square.
   */
  @Getter private final int swX;

  /**
   * The south-west {@code Y} corner of the square.
   */
  @Getter private final int swY;

  /**
   * The north-east {@code X} corner of the square.
   */
  @Getter private final int neX;

  /**
   * The north-east {@code Y} corner of the square.
   */
  @Getter private final int neY;

  /**
   * The {@code Z} level of the box.
   */
  private final int height;

  /**
   * Creates a new {@link SquareLocation} with a default height of {@code 0}.
   *
   * @param swX the south-west {@code X} corner of the square.
   * @param swY the south-west {@code Y} corner of the square.
   * @param neX the north-east {@code X} corner of the square.
   * @param neY the north-east {@code Y} corner of the square.
   */
  public SquareArea(int swX, int swY, int neX, int neY) {
    this("Unknown", swX, swY, neX, neY, 0);
  }

  /**
   * Creates a new {@link SquareLocation} with a default height of {@code 0}.
   * 
   * @param name The name of this area.
   *
   * @param swX the south-west {@code X} corner of the square.
   * @param swY the south-west {@code Y} corner of the square.
   * @param neX the north-east {@code X} corner of the square.
   * @param neY the north-east {@code Y} corner of the square.
   */
  public SquareArea(String name, int swX, int swY, int neX, int neY) {
    this(name, swX, swY, neX, neY, 0);
  }

  /**
   * Creates a new {@link SquareLocation}.
   * 
   * @param name The name of this area.
   *
   * @param swX the south-west {@code X} corner of the square.
   * @param swY the south-west {@code Y} corner of the square.
   * @param neX the north-east {@code X} corner of the square.
   * @param neY the north-east {@code Y} corner of the square.
   * @param height the {@code Height} level of the square.
   */
  public SquareArea(String name, int swX, int swY, int neX, int neY, int height) {
    this.name = name;
    this.swX = swX;
    this.swY = swY;
    this.neX = neX;
    this.neY = neY;
    this.height = height;
  }

  @Override
  public boolean inArea(Position location) {
    if (location.getHeight() != height) {
      return false;
    }
    return location.getX() >= swX && location.getX() <= neX && location.getY() >= swY
        && location.getY() <= neY;
  }

}
