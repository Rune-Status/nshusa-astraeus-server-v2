package io.astraeus.game.world.entity.mob.combat.def;

public final class CombatAnimation {

  private final int block;

  private final int stand;

  private final int walk;

  private final int run;

  public CombatAnimation(int block, int stand, int walk, int run) {
    this.block = block;
    this.stand = stand;
    this.walk = walk;
    this.run = run;
  }

  public int getBlock() {
    return block;
  }

  public int getStand() {
    return stand;
  }

  public int getWalk() {
    return walk;
  }

  public int getRun() {
    return run;
  }


}
