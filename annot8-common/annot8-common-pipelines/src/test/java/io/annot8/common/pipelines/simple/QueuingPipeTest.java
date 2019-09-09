/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class QueuingPipeTest {

  @Mock QueueFeeder feeder;

  @Mock Pipe pipe;

  @Mock Item item;

  @Test
  void process() throws Annot8Exception {
    QueuingPipe qp = new QueuingPipe(feeder, pipe);

    qp.process(item);

    // Verify we process the item AND the feed
    verify(pipe).process(item);
    verify(feeder).feed(pipe);
  }
}
