/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.common.pipelines.events.SourceEvent;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.helpers.WithProcessItem;

public class ProcessQueueSourceListener implements SourceListener {

  private final QueueFeeder queueFeeder;
  private final WithProcessItem pipe;

  public ProcessQueueSourceListener(QueueFeeder queuePusher, WithProcessItem pipe) {
    this.queueFeeder = queuePusher;
    this.pipe = pipe;
  }

  @Override
  public void onSourceEvent(SourceEvent event) {
    processQueue();
  }

  protected void processQueue() {
    if (queueFeeder != null && pipe != null) {
      queueFeeder.feed(pipe);
    }
  }
}
