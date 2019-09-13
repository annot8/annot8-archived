/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import java.util.function.Consumer;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;

public class NotifyingItemFactory implements ItemFactory, Listenable<Consumer<Item>> {

  private final Listeners<Consumer<Item>, Item> listeners = new Listeners<>(Consumer::accept);
  private final ItemFactory itemFactory;

  public NotifyingItemFactory(ItemFactory itemFactory) {
    this.itemFactory = itemFactory;
  }

  public Deregister register(Consumer<Item> consumer) {
    return listeners.register(consumer);
  }

  public void deregister(Consumer<Item> consumer) {
    listeners.deregister(consumer);
  }

  @Override
  public Item create() {
    Item item = itemFactory.create();
    listeners.fire(item);
    return item;
  }

  @Override
  public Item create(Item parent) {
    Item item = itemFactory.create(parent);
    listeners.fire(item);
    return item;
  }
}