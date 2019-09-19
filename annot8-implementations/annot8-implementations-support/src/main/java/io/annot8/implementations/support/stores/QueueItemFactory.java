/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.support.stores;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;

public class QueueItemFactory extends NotifyingItemFactory {

  private final Queue<Item> queue;

  public QueueItemFactory(ItemFactory itemFactory) {
    this(itemFactory, new LinkedList<>());
  }

  public QueueItemFactory(ItemFactory itemFactory, Queue<Item> queue) {
    super(itemFactory);
    this.queue = queue;
  }

  public void clear() {
    queue.clear();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public Optional<Item> next() {
    return Optional.ofNullable(queue.poll());
  }
}
