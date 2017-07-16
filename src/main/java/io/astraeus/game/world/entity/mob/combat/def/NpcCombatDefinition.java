package io.astraeus.game.world.entity.mob.combat.def;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.astraeus.game.world.entity.mob.player.skill.SkillData;
import lombok.Data;

@Data
public final class NpcCombatDefinition {
  
  public static final Map<Integer, NpcCombatDefinition> definitions = new HashMap<>();
  
  public static enum ImmuneType {
    POISON,
    
    VENOM,
    
    BOTH,
    
    NONE
  }

  private final int id;

  private final String name;
  
  private final int hitpoints;
  
  private final boolean aggressive;
  
  private final boolean poisonous;
  
  private final int attackSpeed;
  
  private final int[] hitDelays;
  
  private final int maxHit;
  
  private final int respawnTime;
  
  private final int defenceAnimation;
  
  private final int deathAnimation;
  
  private final int[] attackAnimations;
  
  private final ImmuneType immunity;
  
  private final SkillData[] weakness;

  private final NpcCombatBonus bonuses;
  
  public NpcCombatDefinition(int id, String name, int hitpoints, boolean aggressive, boolean poisonous, int attackSpeed, int[] hitDelays, int maxHit, int respawnTime, int defenceAnimation, int deathAnimation, int[] attackAnimations, ImmuneType immunity, SkillData[] weakness, NpcCombatBonus bonuses) {
    this.id = id;
    this.name = name;
    this.hitpoints = hitpoints;
    this.aggressive = aggressive;
    this.poisonous = poisonous;
    this.attackSpeed = attackSpeed;
    this.hitDelays = hitDelays;
    this.maxHit = maxHit;
    this.respawnTime = respawnTime;
    this.defenceAnimation = defenceAnimation;
    this.deathAnimation = deathAnimation;
    this.attackAnimations = attackAnimations;
    this.immunity = immunity;
    this.weakness = weakness;
    this.bonuses = bonuses;
  }
  
  public static Optional<NpcCombatDefinition> lookup(int id) {
    return Optional.ofNullable(definitions.get(id));
  }
  
  @Data
  public static final class NpcCombatBonus {

    private final CombatBonus combat;
    
    private final AggressiveBonus aggressive;
    
    private final DefensiveBonus defensive;
    
    private final OtherBonus other;

    public NpcCombatBonus(CombatBonus combat, AggressiveBonus aggressive, DefensiveBonus defensive,
        OtherBonus other) {
      this.combat = combat;
      this.aggressive = aggressive;
      this.defensive = defensive;
      this.other = other;
    }
    
  }
  
  @Data
  public static final class AggressiveBonus {
    
    private final int stab;
    
    private final int slash;
    
    private final int crush;
    
    private final int magic;
    
    private final int range;

    public AggressiveBonus(int stab, int slash, int crush, int magic, int range) {
      this.stab = stab;
      this.slash = slash;
      this.crush = crush;
      this.magic = magic;
      this.range = range;
    }
    
  }
  
  @Data
  public static final class DefensiveBonus {
    
    private final int stab;
    
    private final int slash;
    
    private final int crush;
    
    private final int magic;
    
    private final int range;

    public DefensiveBonus(int stab, int slash, int crush, int magic, int range) {
      this.stab = stab;
      this.slash = slash;
      this.crush = crush;
      this.magic = magic;
      this.range = range;
    }
  }
  
  @Data
  public static final class CombatBonus {
    
    private final int attack;
    
    private final int strength;
    
    private final int defence;
    
    private final int range;
    
    private final int magic;

    public CombatBonus(int attack, int strength, int defence, int range, int magic) {
      this.attack = attack;
      this.strength = strength;
      this.defence = defence;
      this.range = range;
      this.magic = magic;
    } 
    
  }
  
  @Data
  public static final class OtherBonus {
    
    private final int strength;
    
    private final int rangedStrength;
    
    private final int attack;

    public OtherBonus(int strength, int rangedStrength, int attack) {
      this.strength = strength;
      this.rangedStrength = rangedStrength;
      this.attack = attack;
    }
          
  }

}
