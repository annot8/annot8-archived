/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.events.source.SourceDoneEvent;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.core.helpers.WithProcessItem;

@ExtendWith(MockitoExtension.class)
class ProcessQueueSourceListenerTest {

  @Mock WithProcessItem pipe;

  @Mock QueueFeeder feeder;

  @Test
  void processQueueOnSourceEvent() {

    ProcessQueueSourceListener l = new ProcessQueueSourceListener(feeder, pipe);

    l.onSourceEvent(new SourceDoneEvent(null));

    verify(feeder).feed(pipe);
  }
}
