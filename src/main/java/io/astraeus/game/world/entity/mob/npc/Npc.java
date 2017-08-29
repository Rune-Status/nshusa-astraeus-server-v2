package io.astraeus.game.world.entity.mob.npc;

import java.util.Objects;
import java.util.Optional;

import io.astraeus.cache.impl.def.NpcDefinition;
import io.astraeus.game.GameConstants;
import io.astraeus.game.world.Direction;
import io.astraeus.game.world.Position;
import io.astraeus.game.world.World;
import io.astraeus.game.world.entity.EntityType;
import io.astraeus.game.world.entity.Graphic;
import io.astraeus.game.world.entity.mob.Animation;
import io.astraeus.game.world.entity.mob.Mob;
import io.astraeus.game.world.entity.mob.combat.def.NpcCombatDefinition;
import io.astraeus.game.world.entity.mob.combat.dmg.Hit;
import io.astraeus.game.world.entity.mob.combat.task.MobDeathTask;
import io.astraeus.game.world.entity.mob.update.UpdateFlag;
import io.astraeus.game.world.location.Area;
import io.astraeus.util.Stopwatch;
import lombok.Getter;
import lombok.Setter;

public class Npc extends Mob {

  @Getter
  private Direction facingDirection = Direction.SOUTH;

  @Getter
  @Setter
  private int maximumHealth;

  @Getter
  @Setter
  private int currentHealth;

  @Getter
  @Setter
  private int respawnTimer;

  @Getter
  private final Stopwatch randomWalkTimer = Stopwatch.start();

  @Getter
  @Setter
  private boolean following;
  
  @Getter @Setter
  private int radius;

  public Npc(int id) {
    super(GameConstants.DEFAULT_LOCATION);
    setId(id);
    
    Optional<NpcCombatDefinition> npcCombatDef = NpcCombatDefinition.lookup(id);
    
    currentHealth = npcCombatDef.isPresent() ? npcCombatDef.get().getHitpoints() : 100;
    maximumHealth = npcCombatDef.isPresent() ? npcCombatDef.get().getHitpoints() : 100;
  }

  @Override
  public void onDeath() {

  }

  @Override
  public void onMovement() {

  }

  @Override
  public int size() {
    return NpcDefinition.lookup(getId()).tilesOccupied;
  }

  public String getName() {
    return NpcDefinition.lookup(getId()).getName();
  }

  @Override
  public void preUpdate() {

    movement.handleEntityMovement();

    tick();

    if (radius == 0 && getInteractingEntity() == null && getTick() % 5 == 4) {
      Npcs.resetFacingDirection(this);
    }

    if (!isDead() && getInteractingEntity() == null) {
      resetEntityInteraction();
    }

    if (radius != 0 && getInteractingEntity() == null && getTick() % 5 == 4) {
      Npcs.handleRandomWalk(this);
    }
  }

  @Override
  public void postUpdate() {
    getUpdateFlags().clear();
    animation = Animation.RESET;
    graphic = Graphic.RESET;
  }

  public void setFacingDirection(Direction facingDirection) {
    this.facingDirection = facingDirection;
    getUpdateFlags().add(UpdateFlag.FACE_COORDINATE);
  }

  /**
   * The mob should walk to home
   * 
   * @return If the mob can walk to home or not
   */
  public boolean isWalkToHome() {
    if (Area.inWilderness(this)) {
      return Math.abs(getPosition().getX() - createdPosition.getX())
          + Math.abs(getPosition().getY() - createdPosition.getY()) > size() * 1 + 2;
    }

    if (isNpc()) { // TODO: isAttackable
      return Math.abs(getPosition().getX() - createdPosition.getX())
          + Math.abs(getPosition().getY() - createdPosition.getY()) > size() * 2 + 6;
    }

    return Position.getManhattanDistance(createdPosition, getPosition()) > 2;
  }

  @Override
  public void onTick() {

  }

  @Override
  public void dealDamage(Mob attacker, Hit hit) {
    
    if (isDead()) {
      return;
    }
    
    if (getCurrentHealth() - hit.getDamage() <= 0) {
      hit.setDamage(getCurrentHealth());
      setDead(true);
      World.submit(new MobDeathTask(attacker, this));
    }

    setCurrentHealth(getCurrentHealth() - hit.getDamage());

    hitQueue.add(hit);
    getUpdateFlags().add(UpdateFlag.HIT);
  }

  @Override
  public EntityType type() {
    return EntityType.NPC;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getSlot());
  }

  public String toString() {
    return String.format("[MOB] - [name= %s] [id= %d] [slot= %d] [location= %s]", getName(),
        getId(), getSlot(), getPosition().toString());
  }

}
