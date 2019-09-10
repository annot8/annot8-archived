/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.common.implementations.stores.NotifyingItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.core.data.ItemFactory;

public class QueuingSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueuingSupport.class);

  private final ItemQueueReader queueReader;
  private final NotifyingItemFactory itemFactory;
  private final QueueFeeder queueFeeder;

  public QueuingSupport(ItemQueue queue, ItemFactory itemFactory) {

    this.itemFactory = new NotifyingItemFactory(itemFactory);
    this.itemFactory.register(queue::add);

    this.queueReader = new SimpleItemQueueReader(queue);

    this.queueFeeder = new QueueFeeder(queueReader);

  }

  public ItemQueueReader getQueueReader() {
    return queueReader;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

  public QueueFeeder getQueueFeeder() {
    return queueFeeder;
  }
}
