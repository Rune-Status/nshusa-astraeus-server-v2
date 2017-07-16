package io.astraeus.game.sync.task;

import java.util.concurrent.Phaser;

import io.astraeus.game.world.entity.mob.Mob;

/**
 * The task that occurs before mobs have been synchronized with the client.
 *
 * @author Vult-R
 */
public final class PreMobUpdateTask<T extends Mob> implements Synchronizable {

  /**
   * The mob that this task is for.
   */
  private final T mob;

  /**
   * The phaser to keep this task in sync.
   */
  private final Phaser phaser;

  /**
   * Creates a new {@link PostMobUpdateTask}.
   *
   * @param mob The mob to update.
   *
   * @param phaser The phaser used to sync this task.
   */
  public PreMobUpdateTask(T mob, Phaser phaser) {
    this.mob = mob;
    this.phaser = phaser;
  }

  @Override
  public void run() {
    mob.preUpdate();
    phaser.arriveAndDeregister();
  }

}
