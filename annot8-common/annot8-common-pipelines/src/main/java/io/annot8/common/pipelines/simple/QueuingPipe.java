/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.delegates.DelegatingPipe;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

public class QueuingPipe extends DelegatingPipe implements Pipe {

  private final QueueFeeder queueFeeder;

  public QueuingPipe(QueueFeeder queueFeeder, Pipe delegate) {
    super(delegate);
    this.queueFeeder = queueFeeder;
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    ProcessorResponse response = super.process(item);

    queueFeeder.feed(getDelegate());

    return response;
  }
}
