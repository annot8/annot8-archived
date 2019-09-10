/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.core.data.Item;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MemoryItemQueue implements ItemQueue {

  private final Deque<Item> items = new ConcurrentLinkedDeque<>();

  public void add(Item item) {
    items.addLast(item);
  }

  public boolean hasItems() {
    return !items.isEmpty();
  }

  public Item next() {
    return items.pop();
  }
}
