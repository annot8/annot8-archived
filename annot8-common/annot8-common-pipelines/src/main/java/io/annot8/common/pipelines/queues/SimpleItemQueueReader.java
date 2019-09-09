/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.common.implementations.data.BaseItemToItem;
import io.annot8.core.data.Item;

public class SimpleItemQueueReader implements ItemQueueReader {

  private final BaseItemQueue queue;
  private final BaseItemToItem converter;

  public SimpleItemQueueReader(BaseItemQueue queue, BaseItemToItem converter) {
    this.queue = queue;
    this.converter = converter;
  }

  @Override
  public boolean hasItems() {
    return queue.hasItems();
  }

  @Override
  public Item next() {
    return converter.convert(queue.next());
  }
}
