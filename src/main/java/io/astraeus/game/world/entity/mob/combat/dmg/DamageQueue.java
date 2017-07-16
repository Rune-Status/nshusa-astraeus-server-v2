package io.astraeus.game.world.entity.mob.combat.dmg;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.astraeus.game.world.entity.mob.Mob;
import lombok.Getter;

public final class DamageQueue {

  class DamageEntry implements Comparable<DamageEntry> {
    
    @Getter private final Mob mob;    
    @Getter private int damage;

    public DamageEntry(Mob entity, int damage) {
      this.mob = entity;
      this.damage = damage;
    }

    public void addDamage(int damage) {
      this.damage += damage;
    }

    @Override
    public int compareTo(DamageEntry other) {
      if (other == null || other.getMob() == null || !other.getMob().isRegistered()) {
        return -1;
      }
      return Integer.signum(other.damage - damage);
    }

    @Override
    public int hashCode() {
      return Objects.hash(mob, damage);
    }
    
    @Override
    public boolean equals(Object obj) {
      if (obj instanceof DamageEntry) {
        DamageEntry other = (DamageEntry) obj;
        return other.mob.equals(mob) && other.damage == damage;
      }
      return false;
    }

  }

  private final Map<Mob, DamageEntry> attackers = new LinkedHashMap<>();

  public void addDamage(Mob attacker, int damage) {
    final DamageEntry entry = new DamageEntry(attacker, damage);

    if (attackers.containsKey(attacker)) {
      entry.addDamage(attackers.get(attacker).getDamage());
    }

    attackers.put(attacker, entry);
  }

  public Optional<Mob> getHighestDamager() {
    if (attackers.isEmpty()) {
      return Optional.empty();
    }

    DamageEntry pWinner = null;
    DamageEntry mWinner = null;

    for (Mob entity : attackers.keySet()) {

      if (entity == null || !entity.isRegistered()) {
        continue;
      }

      DamageEntry next = attackers.get(entity);

      if (entity.isPlayer()) {
        if (pWinner == null || pWinner.getDamage() < next.getDamage()) {
          pWinner = next;
        }
      }

      if (entity.isMob()) {
        if (mWinner == null || mWinner.getDamage() < next.getDamage()) {
          mWinner = next;
        }
      }
    }

    if (pWinner == null && mWinner == null) {
      return Optional.empty();
    }

    return Optional.of(pWinner == null ? mWinner.getMob() : pWinner.getMob());
  }

  public void reset() {
    attackers.clear();
  }

}

