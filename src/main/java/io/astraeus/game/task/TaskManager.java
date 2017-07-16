package io.astraeus.game.task;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The class that will manage all tasks.
 *
 * @author Vult-R
 */
public final class TaskManager {

  /**
   * The queue that holds the tasks awaiting to be executed.
   */
  private final Queue<Task> adding = new ConcurrentLinkedQueue<Task>();

  /**
   * The list of tasks that are being processed.
   */
  private final List<Task> tasks = new LinkedList<Task>();

  /**
   * The method that will process all tasks.
   */
  public void runTaskIteration() {
    Task t;

    synchronized (adding) {
      while ((t = adding.poll()) != null) {
        tasks.add(t);
      }
    }

    final Iterator<Task> itr = tasks.iterator();

    while (itr.hasNext()) {
      final Task task = itr.next();

      if (task == null) {
        itr.remove();
        continue;
      }

      try {

        if (task.hasStopped()) {
          task.onStop();
          itr.remove();
          continue;
        }

        task.run();
      } catch (final Exception ex) {
        task.onException(ex);
        ex.printStackTrace();
        itr.remove();
      }
    }

  }

  /**
   * Queues a task to be sequenced with the main game loop.
   *
   * @param task The task to queue.
   */
  public void queue(Task task) {
    if (task.hasStopped()) {
      return;
    }

    task.onStart();

    if (task.isImmediate()) {
      task.execute();      
    }

    adding.add(task);

  }

}
