/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.factories.NotifyingBaseItemFactory;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.core.data.ItemFactory;

public class QueuingSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueuingSupport.class);

  private final ItemQueueReader queueReader;
  private final NotifyingBaseItemFactory baseItemFactory;
  private final QueueFeeder queueFeeder;
  private final WrappingBaseItemToItem converter;
  private final ItemFactory itemFactory;

  public QueuingSupport(BaseItemQueue queue, BaseItemFactory baseItemFactory) {

    this.baseItemFactory = new NotifyingBaseItemFactory(baseItemFactory);
    this.baseItemFactory.register(queue::add);

    this.converter = new WrappingBaseItemToItem(this.baseItemFactory);

    this.queueReader = new SimpleItemQueueReader(queue, converter);

    this.queueFeeder = new QueueFeeder(queueReader);

    this.itemFactory = new SimpleItemFactory(this.baseItemFactory, this.converter);
  }

  public BaseItemToItem getConverter() {
    return converter;
  }

  public ItemQueueReader getQueueReader() {
    return queueReader;
  }

  public BaseItemFactory getBaseItemFactory() {
    return baseItemFactory;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

  public QueueFeeder getQueueFeeder() {
    return queueFeeder;
  }
}
