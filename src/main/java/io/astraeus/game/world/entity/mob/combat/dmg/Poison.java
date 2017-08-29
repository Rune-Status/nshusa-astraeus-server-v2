package io.astraeus.game.world.entity.mob.combat.dmg;

import io.astraeus.cache.impl.def.ItemDefinition;
import io.astraeus.util.RandomUtils;
import lombok.Getter;

/**
 * Represents a detriment that can damage mobs for a period of time.
 * 
 * @author Vult-R
 */
public final class Poison {

  public static enum PoisonType {

    NONE(0),

    REGULAR(1),

    VENOM(2);

    /**
     * The code that indicates the poisons type.
     */
    @Getter private final int type;

    private PoisonType(int type) {
      this.type = type;
    }

  }

  /**
   * Represents the type of poison according to RuneScape.
   */
  public static enum DamageTypes {

    /**
     * Represents the type of poison which deals 1's and 2's.
     */
    WEAK(new int[] {1, 2}),

    /**
     * Represents the type of poison which deals 4's and 2's.
     */
    DEFAULT(new int[] {4, 2}),

    /**
     * Represents the type of poison which deals 5's and 3's.
     */
    STRONG(new int[] {5, 3}),

    /**
     * Represents the type of poison which deals 6's and 4's.
     */
    SUPER(new int[] {6, 4}),

    /**
     * Represents the type of poison that deals 8's and 6's.
     */
    NPC(new int[] {8, 6});

    /**
     * The possible hits for this type.
     */
    @Getter private final int[] hits;

    /**
     * Creates a new {@link DamageTypes}.
     * 
     * @param hits The possible hits for this type.
     */
    private DamageTypes(int[] hits) {
      this.hits = hits;
    }

    /**
     * Gets a random damage.
     * 
     * @return The damage.
     */
    public int getDamage() {
      for (Integer damage : hits) {
        if (RandomUtils.random(hits.length) == 1) {
          return damage;
        }
      }
      return hits[0];

    }

  }

  /**
   * Gets the type of poison for a specified weapon.
   * 
   * @param id The id of the weapon.
   * 
   * @return The strength of the poison.
   */
  public static DamageTypes getPoisonTypeForWeapon(int id) {

    if (Poison.isWeaponPoisonous(id)) {

      String name = ItemDefinition.lookup(id).getName();

      if (name.contains("(p)")) {
        return DamageTypes.WEAK;
      } else if (name.contains("(p+)")) {
        return DamageTypes.STRONG;
      } else if (name.contains("(p++)")) {
        return DamageTypes.SUPER;
      }

    }
    return DamageTypes.DEFAULT;
  }

  /**
   * Determines if a weapon if poisonous
   * 
   * @param id The id of the weapon to check.
   */
  public static boolean isWeaponPoisonous(int id) {

    if (ItemDefinition.lookup(id) == null) {
      return false;
    }

    ItemDefinition def = ItemDefinition.lookup(id);

    return def.getName().contains("(p)") || def.getName().contains("(p+)")
        || def.getName().contains("(p++)");
  }

}

