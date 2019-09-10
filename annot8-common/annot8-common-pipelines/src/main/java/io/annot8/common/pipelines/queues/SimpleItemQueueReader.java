/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.core.data.Item;

public class SimpleItemQueueReader implements ItemQueueReader {

  private final ItemQueue queue;
  public SimpleItemQueueReader(ItemQueue queue) {
    this.queue = queue;
  }

  @Override
  public boolean hasItems() {
    return queue.hasItems();
  }

  @Override
  public Item next() {
    return queue.next();
  }
}
