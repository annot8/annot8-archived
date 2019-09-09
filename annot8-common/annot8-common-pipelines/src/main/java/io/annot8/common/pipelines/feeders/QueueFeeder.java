/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.common.pipelines.events.SourceEvent;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.common.pipelines.queues.ItemQueueReader;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public class QueueFeeder implements ItemFeeder {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueueFeeder.class);

  private final Listeners<SourceListener, SourceEvent> listeners =
      new Listeners<>(SourceListener::onSourceEvent);
  private final ItemQueueReader queueReader;

  public QueueFeeder(ItemQueueReader reader) {
    this.queueReader = reader;
  }

  public void feed(WithProcessItem pipe) {
    while (queueReader.hasItems()) {

      // TODO: We don't fire any source tasks here, but since we don't have a source

      Item item = queueReader.next();

      try {
        pipe.process(item);
      } catch (Annot8Exception e) {
        LOGGER.error("Failed to process item {} on queue", item.getId(), e);
      }
    }
  }

  @Override
  public void close() {
    // Do nothing
  }

  @Override
  public Deregister register(SourceListener listener) {
    return listeners.register(listener);
  }

  @Override
  public void deregister(SourceListener listener) {
    listeners.deregister(listener);
  }
}
