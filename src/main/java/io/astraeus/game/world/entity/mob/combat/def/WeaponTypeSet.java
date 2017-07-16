package io.astraeus.game.world.entity.mob.combat.def;

import java.util.HashMap;
import java.util.Map;

public final class WeaponTypeSet {

  public static final Map<WeaponType, AttackType[]> definition = new HashMap<>();

  private final WeaponType type;

  private final AttackType[] attackTypes;

  public WeaponTypeSet(WeaponType type, AttackType[] attackTypes) {
    this.type = type;
    this.attackTypes = attackTypes;
  }

  public WeaponType getType() {
    return type;
  }

  public AttackType[] getAttackTypes() {
    return attackTypes;
  }

  public boolean equals(Object o) {
    if (o instanceof WeaponTypeSet) {
      WeaponTypeSet set = (WeaponTypeSet) o;

      if (set.getType() == type && set.getAttackTypes() == attackTypes) {
        return true;
      }
    }
    return false;

  }

}
