package io.astraeus.game.world.entity.mob.combat;

import java.util.EnumSet;

import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.mob.Mob;

/**
 * The class that handles combat cooldowns.
 * 
 * @author Vult-R
 */
public final class CombatCooldown {
  
  private final EnumSet<CombatType> cooldowns = EnumSet.noneOf(CombatType.class);
  
  public void add(CombatType cooldown, int duration, Mob mob) {
    cooldowns.add(cooldown);
    World.submit(new CooldownTask(mob, cooldown, mob.getCombat().getAttackBuilder().getAttackSpeed()));
  }
  
  public void reset(CombatType cooldown) {
    cooldowns.remove(cooldown);
  }
  
  public boolean contains(CombatType cooldown) {
    return cooldowns.contains(cooldown);
  }
  
  public boolean isEmpty() {
    return cooldowns.isEmpty();
  }
  

}
