package io.astraeus.game.sync.task;

import java.util.concurrent.Phaser;

import io.astraeus.game.world.entity.mob.Mob;

/**
 * The task to synchronize mobs with players clients.
 *
 * @author Vult-R
 */
public final class MobUpdateTask<T extends Mob> implements Synchronizable {

  /**
   * The mob to synchronize
   */
  private final T mob;

  /**
   * The phaser that will keep this task in sync.
   */
  private final Phaser phaser;

  /**
   * Creates a new {@link MobUpdateTask}
   *
   * @param mob The mob to synchronize
   *
   * @param phaser The phaser that will keep this task in sync.
   */
  public MobUpdateTask(T mob, Phaser phaser) {
    this.mob = mob;
    this.phaser = phaser;
  }

  @Override
  public void run() {
    mob.update();
    phaser.arriveAndDeregister();
  }

}
