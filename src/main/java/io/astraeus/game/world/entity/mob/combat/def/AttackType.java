package io.astraeus.game.world.entity.mob.combat.def;

import io.astraeus.game.world.entity.mob.combat.Combat;

/**
 * The enumerated type whose elements represent the fighting types.
 *
 * @author lare96 <http://github.com/lare96>
 */
public enum AttackType {
  STAFF_BASH(406, 43, 0, Combat.ATTACK_CRUSH, AttackStyle.ACCURATE),
  STAFF_POUND(406, 43, 1, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  STAFF_FOCUS(406, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.DEFENSIVE),
  WARHAMMER_POUND(401, 43, 0, Combat.ATTACK_CRUSH, AttackStyle.ACCURATE),
  WARHAMMER_PUMMEL(401, 43, 1, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  WARHAMMER_BLOCK(401, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.DEFENSIVE),
  SCYTHE_REAP(408, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  SCYTHE_CHOP(451, 43, 1, Combat.ATTACK_STAB, AttackStyle.AGGRESSIVE),
  SCYTHE_JAB(412, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  SCYTHE_BLOCK(408, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  BATTLEAXE_CHOP(1833, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  BATTLEAXE_HACK(1833, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  BATTLEAXE_SMASH(401, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  BATTLEAXE_BLOCK(1833, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  CROSSBOW_ACCURATE(427, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  CROSSBOW_RAPID(427, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  CROSSBOW_LONGRANGE(427, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  SHORTBOW_ACCURATE(426, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  SHORTBOW_RAPID(426, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  SHORTBOW_LONGRANGE(426, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  LONGBOW_ACCURATE(426, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  LONGBOW_RAPID(426, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  LONGBOW_LONGRANGE(426, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  DAGGER_STAB(400, 43, 0, Combat.ATTACK_STAB, AttackStyle.ACCURATE),
  DAGGER_LUNGE(400, 43, 1, Combat.ATTACK_STAB, AttackStyle.AGGRESSIVE),
  DAGGER_SLASH(451, 43, 2, Combat.ATTACK_STAB, AttackStyle.AGGRESSIVE),
  DAGGER_BLOCK(400, 43, 3, Combat.ATTACK_STAB, AttackStyle.DEFENSIVE),
  SWORD_STAB(412, 43, 0, Combat.ATTACK_STAB, AttackStyle.ACCURATE),
  SWORD_LUNGE(412, 43, 1, Combat.ATTACK_STAB, AttackStyle.AGGRESSIVE),
  SWORD_SLASH(451, 43, 2, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  SWORD_BLOCK(412, 43, 3, Combat.ATTACK_STAB, AttackStyle.DEFENSIVE),
  SCIMITAR_CHOP(451, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  SCIMITAR_SLASH(451, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  SCIMITAR_LUNGE(412, 43, 2, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  SCIMITAR_BLOCK(451, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  LONGSWORD_CHOP(451, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  LONGSWORD_SLASH(451, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  LONGSWORD_LUNGE(412, 43, 2, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  LONGSWORD_BLOCK(451, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  MACE_POUND(1833, 43, 0, Combat.ATTACK_CRUSH, AttackStyle.ACCURATE),
  MACE_PUMMEL(401, 43, 1, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  MACE_SPIKE(412, 43, 2, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  MACE_BLOCK(401, 43, 3, Combat.ATTACK_CRUSH, AttackStyle.DEFENSIVE),
  KNIFE_ACCURATE(806, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  KNIFE_RAPID(806, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  KNIFE_LONGRANGE(806, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  SPEAR_LUNGE(2080, 43, 0, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  SPEAR_SWIPE(2081, 43, 1, Combat.ATTACK_SLASH, AttackStyle.CONTROLLED),
  SPEAR_POUND(2082, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.CONTROLLED),
  SPEAR_BLOCK(2080, 43, 3, Combat.ATTACK_STAB, AttackStyle.DEFENSIVE),
  TWOHANDEDSWORD_CHOP(407, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  TWOHANDEDSWORD_SLASH(407, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  TWOHANDEDSWORD_SMASH(406, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  TWOHANDEDSWORD_BLOCK(407, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  PICKAXE_SPIKE(412, 43, 0, Combat.ATTACK_STAB, AttackStyle.ACCURATE),
  PICKAXE_IMPALE(412, 43, 1, Combat.ATTACK_STAB, AttackStyle.AGGRESSIVE),
  PICKAXE_SMASH(401, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  PICKAXE_BLOCK(412, 43, 3, Combat.ATTACK_STAB, AttackStyle.DEFENSIVE),
  CLAWS_CHOP(451, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  CLAWS_SLASH(451, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  CLAWS_LUNGE(412, 43, 2, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  CLAWS_BLOCK(451, 43, 3, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  HALBERD_JAB(412, 43, 0, Combat.ATTACK_STAB, AttackStyle.CONTROLLED),
  HALBERD_SWIPE(440, 43, 1, Combat.ATTACK_SLASH, AttackStyle.AGGRESSIVE),
  HALBERD_FEND(412, 43, 2, Combat.ATTACK_STAB, AttackStyle.DEFENSIVE),
  UNARMED_PUNCH(422, 43, 0, Combat.ATTACK_CRUSH, AttackStyle.ACCURATE),
  UNARMED_KICK(423, 43, 1, Combat.ATTACK_CRUSH, AttackStyle.AGGRESSIVE),
  UNARMED_BLOCK(422, 43, 2, Combat.ATTACK_CRUSH, AttackStyle.DEFENSIVE),
  WHIP_FLICK(1658, 43, 0, Combat.ATTACK_SLASH, AttackStyle.ACCURATE),
  WHIP_LASH(1658, 43, 1, Combat.ATTACK_SLASH, AttackStyle.CONTROLLED),
  WHIP_DEFLECT(1658, 43, 2, Combat.ATTACK_SLASH, AttackStyle.DEFENSIVE),
  THROWNAXE_ACCURATE(806, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  THROWNAXE_RAPID(806, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  THROWNAXE_LONGRANGE(806, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  DART_ACCURATE(806, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  DART_RAPID(806, 43, 1, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  DART_LONGRANGE(806, 43, 2, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE),
  JAVELIN_ACCURATE(806, 43, 0, Combat.ATTACK_RANGED, AttackStyle.ACCURATE),
  JAVELIN_RAPID(806, 43, 2, Combat.ATTACK_RANGED, AttackStyle.AGGRESSIVE),
  JAVELIN_LONGRANGE(806, 43, 3, Combat.ATTACK_RANGED, AttackStyle.DEFENSIVE);

  /**
   * The animation executed when this type is active.
   */
  private final int attackAnimation;

  /**
   * The parent config identification.
   */
  private final int parent;

  /**
   * The child config identification.
   */
  private final int child;

  /**
   * The type of bonus this type will apply.
   */
  private final int bonus;

  /**
   * The style active when this type is active.
   */
  private final AttackStyle style;

  /**
   * Creates a new {@link AttackType}.
   *
   * @param animation the animation executed when this type is active.
   * @param parent the parent config identification.
   * @param child the child config identification.
   * @param bonus the type of bonus this type will apply.
   * @param style the style active when this type is active.
   */
  private AttackType(int attackAnimation, int parent, int child, int bonus, AttackStyle style) {
    this.attackAnimation = attackAnimation;
    this.parent = parent;
    this.child = child;
    this.bonus = bonus;
    this.style = style;
  }

  /**
   * Determines the corresponding bonus for this fight type.
   *
   * @return the corresponding.
   */
  public final int getCorrespondingBonus() {
    switch (bonus) {
      case Combat.ATTACK_CRUSH:
        return Combat.DEFENCE_CRUSH;
      case Combat.ATTACK_MAGIC:
        return Combat.DEFENCE_MAGIC;
      case Combat.ATTACK_RANGED:
        return Combat.DEFENCE_RANGED;
      case Combat.ATTACK_SLASH:
        return Combat.DEFENCE_SLASH;
      case Combat.ATTACK_STAB:
        return Combat.DEFENCE_STAB;
      default:
        return Combat.DEFENCE_CRUSH;
    }
  }

  /**
   * Gets the animation executed when this type is active.
   *
   * @return the animation executed.
   */
  public final int getAttackAnimation() {
    return attackAnimation;
  }

  /**
   * Gets the parent config identification.
   *
   * @return the parent config.
   */
  public final int getParent() {
    return parent;
  }

  /**
   * Gets the child config identification.
   *
   * @return the child config.
   */
  public final int getChild() {
    return child;
  }

  /**
   * Gets the type of bonus this type will apply
   *
   * @return the bonus type.
   */
  public final int getBonus() {
    return bonus;
  }

  /**
   * Gets the style active when this type is active.
   *
   * @return the fighting style.
   */
  public final AttackStyle getStyle() {
    return style;
  }
}
