package io.astraeus.game.world.entity.mob.combat.formula;

import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.combat.Combat;
import io.astraeus.game.world.entity.mob.combat.CombatPrayer.PrayerType;
import io.astraeus.game.world.entity.mob.player.skill.Skill;

public abstract class Formula {

  protected final Combat combat;

  public Formula(Combat combat) {
    this.combat = combat;
  }

  public abstract int calculateMaxHit();

  public abstract boolean isAccurate(Mob attacker, Mob defender);

  public final int getEffectiveAttack(Mob entity) {
    double level = entity.getSkills().getLevel(Skill.ATTACK);

    if (entity.isPlayer()) {
      if (entity.getPlayer().getCombatPrayer().active(PrayerType.CLARITY_OF_THOUGHT)) {
        level *= 1.05;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.IMPROVED_REFLEXES)) {
        level *= 1.10;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.INCREDIBLE_REFLEXES)) {
        level *= 1.15;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.CHIVALRY)) {
        level *= 1.15;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.PIETY)) {
        level *= 1.20;
      }
    }

    return (int) level;
  }

  public final int getEffectiveDefence(Mob entity) {
    double level = entity.getSkills().getLevel(Skill.DEFENCE);

    if (entity.isPlayer()) {
      if (entity.getPlayer().getCombatPrayer().active(PrayerType.THICK_SKIN)) {
        level *= 1.05;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.ROCK_SKIN)) {
        level *= 1.10;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.STEEL_SKIN)) {
        level *= 1.15;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.CHIVALRY)) {
        level *= 1.20;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.PIETY)) {
        level *= 1.25;
      }
    }

    return (int) level;
  }

  public final int getEffectiveMagic(Mob entity) {
    double level = entity.getSkills().getLevel(Skill.MAGIC);

    if (entity.isPlayer()) {
      if (entity.getPlayer().getCombatPrayer().active(PrayerType.MYSTIC_WILL)) {
        level *= 1.05;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.MYSTIC_LORE)) {
        level *= 1.10;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.MYSTIC_MIGHT)) {
        level *= 1.15;
      }
    }

    return (int) level;
  }

  public final int getEffectiveRanged(Mob entity) {
    double level = entity.getSkills().getLevel(Skill.RANGE);

    if (entity.isPlayer()) {
      if (entity.getPlayer().getCombatPrayer().active(PrayerType.SHARP_EYE)) {
        level *= 1.05;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.HAWK_EYE)) {
        level *= 1.10;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.EAGLE_EYE)) {
        level *= 1.15;
      }
    }

    return (int) level;
  }

  public final int getEffectiveStrength(Mob entity) {
    double level = entity.getSkills().getLevel(Skill.STRENGTH);

    if (entity.isPlayer()) {
      if (entity.getPlayer().getCombatPrayer().active(PrayerType.BURST_OF_STRENGTH)) {
        level *= 1.05;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.SUPERHUMAN_STRENGTH)) {
        level *= 1.10;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.ULTIMATE_STRENGTH)) {
        level *= 1.15;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.CHIVALRY)) {
        level *= 1.18;
      } else if (entity.getPlayer().getCombatPrayer().active(PrayerType.PIETY)) {
        level *= 1.23;
      }
    }

    return (int) level;
  }

}
