package io.astraeus.game.world.entity.mob.combat.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.astraeus.game.task.Task;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.item.Item;
import io.astraeus.game.world.entity.mob.Animation;
import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.combat.def.NpcCombatDefinition;
import io.astraeus.game.world.entity.mob.npc.NpcSpawn;
import io.astraeus.game.world.entity.mob.npc.Npcs;
import io.astraeus.game.world.entity.mob.npc.drop.NpcDrop;
import io.astraeus.game.world.entity.mob.npc.drop.NpcDrop.Drop;
import io.astraeus.game.world.entity.mob.player.Player;
import io.astraeus.game.world.entity.object.GameObjects;
import io.astraeus.net.packet.out.AddGroundItemPacket;
import io.astraeus.util.RandomUtils;

public final class MobDeathTask extends Task {

  private final Mob attacker;
  private final Mob victim;  
  
  private final NpcSpawn spawn;

  private final Optional<NpcCombatDefinition> npcCombatDef;
  
  private final int spawnTime;
  
  private final Optional<Drop[]> drop;
  
  private int timer = 0;
  
  public MobDeathTask(Mob attacker, Mob victim) {    
    super(0, true);
    this.attacker= attacker;
    this.victim = victim;
    this.spawn = victim.isNpc() ? new NpcSpawn(victim.getId(), victim.getCreatedPosition()) : null;
    this.npcCombatDef = victim.isNpc() ? NpcCombatDefinition.lookup(victim.getId()) : Optional.empty();
    this.spawnTime = victim.isNpc() ? npcCombatDef.isPresent() ? npcCombatDef.get().getRespawnTime() : 20 : 0;
    this.drop = victim.isNpc() ? NpcDrop.lookup(victim.getId()) : Optional.empty();
  }

  @Override
  public void execute() {
    
    System.out.println("tick: " + timer);
    
    switch (timer) {
      
      case 1:
        int deathAnimation = victim.getCombat().getDeathAnimation();
        victim.startAnimation(Animation.create(deathAnimation));
        break;
        
      case 4:
       World.deregister(victim);
        break;
        
      case 5:
        drop.ifPresent(it -> {
          
          Set<Item> items = new HashSet<>();
          
          Arrays.stream(it).forEach(d -> {            
            
            int random = RandomUtils.random(1, d.getRate());
            
            if (random == d.getRate()) {
            
              if (attacker.isPlayer()) {
                
                Player player = attacker.getPlayer();
                
                Item item = new Item(d.getItemId(), d.isSingle() ? 1 : d.isFixed() ? d.getMaxAmount() : RandomUtils.random(1, d.getMaxAmount()));
                
                player.queuePacket(new AddGroundItemPacket(victim.getPosition().copy(), item));
                
                items.add(item);
              }
              
            }
            
          });
          
          GameObjects.getGroundItems().put(victim.getPosition().copy(), items.stream().toArray(Item[]::new));
          
        });
        break;
        
        default:          
          break;
      
    }
    
    // respawn again
    if (timer >= spawnTime) {
      Npcs.createSpawn(spawn);
      stop();
    }

    timer++;

  }

}
