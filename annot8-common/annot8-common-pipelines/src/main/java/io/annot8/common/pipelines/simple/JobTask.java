/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.base.AbstractTask;
import io.annot8.common.pipelines.elements.Job;

public class JobTask extends AbstractTask {

  private final Job job;

  public JobTask(Job job) {
    super(job.getId(), job.getName());
    this.job = job;
  }

  @Override
  protected void perform() {
    job.run();
  }

  @Override
  public void close() {
    job.close();
  }
}
