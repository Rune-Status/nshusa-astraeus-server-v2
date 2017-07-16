package io.astraeus.game.task;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * An abstract model that represents units of work to be carried out in sequence to game ticks.
 *
 * @author Freyr
 */
public abstract class Task {

  /**
   * The default instance this task is locked to if one isn't specified.
   */
  public static final Object ATTACHMENT = new Object();
  
  /**
   * The amount of times this task has been executed.
   */
  protected int tick = -1;

  /**
   * The flag that denotes this task has stopped.
   */
  private boolean stopped = false;

  /**
   * The flag that denotes to interrupt this task.
   */
  @Getter @Setter private boolean interrupt;

  /**
   * The time in ticks to delay this task from starting.
   */
  @Getter private int delay;

  /**
   * The flag that indicates this task is immediate.
   */
  @Getter protected final boolean immediate;

  /**
   * Creates a new {@link Task}.
   *
   * @param delay The delay in game ticks until this task can execute.
   * @param immediate The flag that denotes to execute this task immediately.
   */
  public Task(int delay, boolean immediate) {
    this.delay = delay;
    this.immediate = immediate;
  }

  /**
   * The method called this task executes.
   */
  public abstract void execute();

  /**
   * The method called when a task stops.
   */
  public void onStop() {

  }

  /**
   * The method called when an exception occurs.
   */
  public void onException(Exception ex) {

  }

  /**
   * The method that is called when the task starts.
   */
  public void onStart() {}

  /**
   * The method called when the task runs.
   */
  public final void run() {
	tick++;
    if (tick >= delay) {
      if (interrupt) {
        stop();
        return;
      }
      execute();
    }   
  }

  /**
   * Sets the delay for this task.
   *
   * @param ticks The ticks to delay this task for.
   */
  public final void setDelay(int ticks) {
    if (ticks < 0) {
      throw new IllegalArgumentException("Tick amount must be positive.");
    }

    this.delay = ticks;
  }

  /**
   * Stops this task.
   */
  public final void stop() {
    stopped = true;
  }

  /**
   * Determines if this task has stopped.
   */
  public final boolean hasStopped() {
    return stopped;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ATTACHMENT);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Task) {

      Task task = (Task) o;

      if (task.hashCode() == hashCode()) {
        return true;
      }

    }
    return false;
  }

}
