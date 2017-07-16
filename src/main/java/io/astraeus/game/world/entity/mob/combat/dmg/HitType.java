package io.astraeus.game.world.entity.mob.combat.dmg;

import lombok.Getter;

/**
 * Represents a type of hit splat
 *
 * @author Vult-R
 */
public enum HitType {
  BLOCKED(0),
  NORMAL(1),
  POISON(2),
  VENOM(3),
  CRITICAL(4);

  @Getter private int id;

  private HitType(int id) {
    this.id = id;
  }

}
