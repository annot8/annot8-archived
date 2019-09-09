/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.List;
import java.util.UUID;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.common.pipelines.elements.Job;
import io.annot8.common.pipelines.elements.Task;
import io.annot8.common.pipelines.events.JobEvent;
import io.annot8.common.pipelines.events.jobs.JobBegunEvent;
import io.annot8.common.pipelines.events.jobs.JobCompleteEvent;
import io.annot8.common.pipelines.listeners.JobListener;

public class SimpleJob implements Job {

  private final Listeners<JobListener, JobEvent> listeners =
      new Listeners<>(JobListener::onJobEvent);

  private final String name;
  private final List<Task> tasks;

  private final String id;

  public SimpleJob(String name, List<Task> tasks) {
    this.name = name;
    this.tasks = tasks;
    this.id = UUID.randomUUID().toString();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Deregister register(JobListener listener) {
    return listeners.register(listener);
  }

  @Override
  public void deregister(JobListener listener) {
    listeners.deregister(listener);
  }

  @Override
  public void run() {
    // TODO: This is very basic - we could listen for TaskCompleteEvent
    listeners.fire(new JobBegunEvent(this));
    for (Task task : tasks) {
      task.run();
    }
    listeners.fire(new JobCompleteEvent(this));
  }

  @Override
  public void close() {
    tasks.forEach(Task::close);
  }
}
