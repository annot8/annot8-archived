/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.tasks;

import io.annot8.common.pipelines.elements.Task;
import io.annot8.common.pipelines.events.TaskEvent;

public class AbstractTaskEvent implements TaskEvent {

  private final Task task;

  public AbstractTaskEvent(Task task) {
    this.task = task;
  }

  @Override
  public Task getTask() {
    return task;
  }
}
