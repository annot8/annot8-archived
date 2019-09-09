/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.jobs;

import io.annot8.common.pipelines.elements.Job;

public class JobBegunEvent extends AbstractJobEvent {

  public JobBegunEvent(Job job) {
    super(job);
  }
}
