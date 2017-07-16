package io.astraeus.game.sync.task;

import java.util.concurrent.Phaser;

import io.astraeus.game.world.entity.mob.Mob;

/**
 * The task that occurs after a mobs have been synchronized with the client.
 *
 * @author Vult-R
 */
public final class PostMobUpdateTask<T extends Mob> implements Synchronizable {

  /**
   * The mob that this task is for.
   */
  private final T mob;

  /**
   * Phaser used to keep this task in sync.
   */
  private final Phaser phaser;

  /**
   * Creates a new {@link PostMobUpdateTask}
   *
   * @param mob The mob this task is for.
   *
   * @param phaser The phaser that will keep this task in sync.
   */
  public PostMobUpdateTask(T mob, Phaser phaser) {
    this.mob = mob;
    this.phaser = phaser;
  }

  @Override
  public void run() {
    mob.postUpdate();
    phaser.arriveAndDeregister();
  }

}
