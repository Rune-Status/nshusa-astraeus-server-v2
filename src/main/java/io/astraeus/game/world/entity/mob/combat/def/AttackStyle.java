package io.astraeus.game.world.entity.mob.combat.def;

import io.astraeus.game.world.entity.mob.combat.CombatType;
import io.astraeus.game.world.entity.mob.player.skill.Skill;

/**
 * The enumerated type whose elements represent the fighting styles.
 *
 * @author lare96 <http://github.com/lare96>
 */
public enum AttackStyle {
  ACCURATE {
    @Override
    public int[] skills(CombatType type) {
      return type == CombatType.RANGE ? new int[] {Skill.RANGE} : new int[] {Skill.ATTACK};
    }
  },
  AGGRESSIVE {
    @Override
    public int[] skills(CombatType type) {
      return type == CombatType.RANGE ? new int[] {Skill.RANGE} : new int[] {Skill.STRENGTH};
    }
  },
  DEFENSIVE {
    @Override
    public int[] skills(CombatType type) {
      return type == CombatType.RANGE ? new int[] {Skill.RANGE, Skill.DEFENCE}
          : new int[] {Skill.DEFENCE};
    }
  },
  CONTROLLED {
    @Override
    public int[] skills(CombatType type) {
      return new int[] {Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE};
    }
  };

  /**
   * Determines which skills will be trained by this fighting style based on {@code type}.
   *
   * @param type the combat type being used to attack.
   * @return the skills that will be trained.
   */
  public abstract int[] skills(CombatType type);
}
