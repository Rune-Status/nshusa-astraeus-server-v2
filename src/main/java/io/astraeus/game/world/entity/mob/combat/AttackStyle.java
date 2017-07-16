package io.astraeus.game.world.entity.mob.combat;

import io.astraeus.game.world.entity.mob.player.collect.Equipment;
import lombok.Getter;

/**
 * Represents an attack style.
 *
 * @author Vult-R
 */
public enum AttackStyle {

  STAB(Equipment.STAB, Equipment.STAB_DEFENSE),

  SLASH(Equipment.SLASH, Equipment.SLASH_DEFENSE),

  CRUSH(Equipment.CRUSH, Equipment.CRUSH_DEFENSE),

  RANGED(Equipment.RANGED, Equipment.RANGED_DEFENSE),

  MAGIC(Equipment.MAGIC, Equipment.MAGIC_DEFENSE);

  @Getter private int offensiveSlot;
  @Getter private int defensiveSlot;

  private AttackStyle(int offensiveSlot, int defensiveSlot) {
    this.offensiveSlot = offensiveSlot;
    this.defensiveSlot = defensiveSlot;
  }

}
