/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import io.annot8.core.data.BaseItem;

public class MemoryItemQueue implements BaseItemQueue {

  private final Deque<BaseItem> items = new ConcurrentLinkedDeque<>();

  public void add(BaseItem item) {
    items.addLast(item);
  }

  public boolean hasItems() {
    return !items.isEmpty();
  }

  public BaseItem next() {
    return items.pop();
  }
}
