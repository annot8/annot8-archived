/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.jobs;

import io.annot8.common.pipelines.elements.Job;

public class JobCompleteEvent extends AbstractJobEvent {

  public JobCompleteEvent(Job job) {
    super(job);
  }
}
