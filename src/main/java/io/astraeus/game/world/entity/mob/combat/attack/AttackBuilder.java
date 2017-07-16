package io.astraeus.game.world.entity.mob.combat.attack;

import java.util.Optional;

import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.Animation;
import io.astraeus.game.world.entity.mob.combat.Combat;
import io.astraeus.game.world.entity.mob.combat.CombatType;
import io.astraeus.game.world.entity.mob.combat.def.NpcCombatDefinition;
import io.astraeus.game.world.entity.mob.combat.def.WeaponDefinition;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.mob.player.collect.Equipment;
import lombok.Getter;

public final class AttackBuilder {

  @Getter private final Combat combat;

  @Getter private CombatType combatType = CombatType.MELEE;

  public AttackBuilder(Combat combat) {
    this.combat = combat;
  }

  public void buildAttack() {

    combat.getMob().startAnimation(Animation.create(getAttackAnimation()));
  }

  public int getAttackAnimation() {

    if (combat.getMob().isNpc()) {

      Optional<NpcCombatDefinition> def = NpcCombatDefinition.lookup(combat.getMob().getId());
      
      if (def.isPresent()) {
        
        switch (combatType) {
            
          case MELEE:
            return def.get().getAttackAnimations()[0];
            
          case RANGE:
            return def.get().getAttackAnimations()[1];
            
          case MAGIC:
            return def.get().getAttackAnimations()[2];
          
        }
        
      }
      
      return 806;
    }

    return combat.getMob().getPlayer().getAttackType().getAttackAnimation();
  }

  public int getAttackSpeed() {
    
    if (combat.getMob().isNpc()) {
      
      Optional<NpcCombatDefinition> def = NpcCombatDefinition.lookup(combat.getMob().getId());
      
      if (def.isPresent()) {
        return def.get().getAttackSpeed();
      }

      return 6;
    }

    Player player = combat.getMob().getPlayer();

    Item item = player.getEquipment().get(Equipment.WEAPON_SLOT);

    if (item == null) {
      return 5;
    }

    WeaponDefinition def = WeaponDefinition.definitions.get(item.getId());

    if (def == null) {
      return 5;
    }

    return def.getSpeed();
  }

}
