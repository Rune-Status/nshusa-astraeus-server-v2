package io.astraeus.game.world.entity.mob.combat;

import lombok.Getter;

public enum CombatType {

  MELEE(4),
  RANGE(4),
  MAGIC(2);

  @Getter private int rate;

  private CombatType(int rate) {
    this.rate = rate;
  }

}
