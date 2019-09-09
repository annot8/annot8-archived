/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.jobs;

import io.annot8.common.pipelines.elements.Job;
import io.annot8.common.pipelines.events.JobEvent;

public abstract class AbstractJobEvent implements JobEvent {

  private final Job job;

  protected AbstractJobEvent(Job job) {
    this.job = job;
  }

  @Override
  public Job getJob() {
    return job;
  }
}
