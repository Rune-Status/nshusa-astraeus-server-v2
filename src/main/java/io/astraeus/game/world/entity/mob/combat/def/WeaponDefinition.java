package io.astraeus.game.world.entity.mob.combat.def;

import java.util.HashMap;
import java.util.Map;

public final class WeaponDefinition {

  public static final Map<Integer, WeaponDefinition> definitions = new HashMap<>();

  private final int id;

  private final String name;

  private final WeaponType type;

  private final boolean twoHanded;

  private final int speed;

  private final int hitDelay;

  private final CombatAnimation combatAnimation;

  public WeaponDefinition(int id, String name, WeaponType type, boolean twoHanded, int speed,
      int hitDelay, CombatAnimation combatAnimation2) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.twoHanded = twoHanded;
    this.speed = speed;
    this.hitDelay = hitDelay;
    this.combatAnimation = combatAnimation2;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public WeaponType getType() {
    return type;
  }

  public boolean isTwoHanded() {
    return twoHanded;
  }

  public int getSpeed() {
    return speed;
  }

  public int getHitDelay() {
    return hitDelay;
  }

  public CombatAnimation getCombatAnimation() {
    return combatAnimation;
  }

}
