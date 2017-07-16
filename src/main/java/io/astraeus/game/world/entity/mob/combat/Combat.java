package io.astraeus.game.world.entity.mob.combat;

import java.util.Optional;

import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.combat.attack.AttackBuilder;
import io.astraeus.game.world.entity.mob.combat.def.NpcCombatDefinition;
import io.astraeus.game.world.entity.mob.combat.formula.impl.MeleeFormula;
import io.astraeus.game.world.entity.mob.combat.task.HitTask;
import io.astraeus.util.Stopwatch;
import lombok.Getter;
import lombok.Setter;

public final class Combat {
  
  public static final int ATTACK_STAB = 0;
  public static final int ATTACK_SLASH = 1;
  public static final int ATTACK_CRUSH = 2;
  public static final int ATTACK_MAGIC = 3;
  public static final int ATTACK_RANGED = 4;
  public static final int DEFENCE_STAB = 5;
  public static final int DEFENCE_SLASH = 6;
  public static final int DEFENCE_CRUSH = 7;
  public static final int DEFENCE_MAGIC = 8;
  public static final int DEFENCE_RANGED = 9;
  public static final int BONUS_STRENGTH = 10;
  public static final int BONUS_PRAYER = 11;

  @Getter private final MeleeFormula meleeFormula = new MeleeFormula(this);

  @Getter private final AttackBuilder attackBuilder = new AttackBuilder(this);

  @Getter private final CombatCooldown combatCooldown = new CombatCooldown();

  @Getter private final Mob mob;

  @Getter @Setter private boolean inCombat;

  @Getter private final Stopwatch combatDelay = Stopwatch.start();

  public Combat(Mob mob) {
    this.mob = mob;
  }

  public void attack(Mob victim) {     
    
    if (combatCooldown.contains(CombatType.MELEE)) {
      return;
    }
    
    inCombat = true;

    mob.startAction(new HitTask(this, victim));

    // TODO mob animation
  }

  public void setCooldown(int delay) {
    if (inCombat && attackBuilder.getCombatType() != null) {
      combatCooldown.add(attackBuilder.getCombatType(), delay, mob);
    }
  }

  public int getDeathAnimation() {
    if (mob.isNpc()) {
      Optional<NpcCombatDefinition> def = NpcCombatDefinition.lookup(mob.getId());
      
      if (def.isPresent()) {
        return def.get().getDeathAnimation();
      }
    }
    return -1;
  }
  
}
