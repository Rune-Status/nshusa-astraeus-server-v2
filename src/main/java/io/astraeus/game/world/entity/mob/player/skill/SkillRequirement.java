package io.astraeus.game.world.entity.mob.player.skill;

import lombok.Data;

/**
 * The skill-related requirement.
 * 
 * @author SeVen
 */
@Data
public class SkillRequirement {

  private final int level;

  private final SkillData skill;

  public SkillRequirement(int level, SkillData skill) {
    this.level = level;
    this.skill = skill;
  }

}

