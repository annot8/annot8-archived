/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import java.util.function.Consumer;

import io.annot8.common.implementations.data.BaseItemFactory;
import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.core.data.BaseItem;

public class NotifyingBaseItemFactory implements BaseItemFactory, Listenable<Consumer<BaseItem>> {

  private final Listeners<Consumer<BaseItem>, BaseItem> listeners =
      new Listeners<>(Consumer::accept);
  private final BaseItemFactory itemFactory;

  public NotifyingBaseItemFactory(BaseItemFactory itemFactory) {
    this.itemFactory = itemFactory;
  }

  public Deregister register(Consumer<BaseItem> consumer) {
    return listeners.register(consumer);
  }

  public void deregister(Consumer<BaseItem> consumer) {
    listeners.deregister(consumer);
  }

  @Override
  public BaseItem create() {
    BaseItem item = itemFactory.create();
    listeners.fire(item);
    return item;
  }

  @Override
  public BaseItem create(BaseItem parent) {
    BaseItem item = itemFactory.create(parent);
    listeners.fire(item);
    return item;
  }
}
