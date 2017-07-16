package io.astraeus.game.task.impl;

import io.astraeus.game.task.Task;

/**
 * The {@link Task} that will only execute one time.
 *
 * @author Vult-R
 */
public abstract class RunOnceTask extends Task {

  /**
   * Creates a new {@link RunOnceTask}.
   */
  public RunOnceTask() {
    this(0, true);
  }

  /**
   * Creates a new {@link RunOnceTask}.
   *
   * @param immediate The flag that indicates this task will be executed right away.
   */
  public RunOnceTask(boolean immediate) {
    this(0, immediate);
  }

  /**
   * Creates a new {@link RunOnceTask}.
   *
   * @param delay The delay in game ticks this task will sleep for
   */
  public RunOnceTask(int delay) {
    this(delay, true);
  }

  /**
   * Creates a new {@link RunOnceTask}.
   *
   * @param delay The delay in game ticks this task will sleep for
   * @param immediate The flag that indicates this task will be executed right away.
   */
  public RunOnceTask(int delay, boolean immediate) {
    super(delay, immediate);
  }

  @Override
  public void execute() {
    stop();
  }

  @Override
  public abstract void onStop();

}
