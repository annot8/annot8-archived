/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.data;

import io.annot8.common.implementations.delegates.DelegateBaseItem;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Item;

public class BaseItemToItemWrapper extends DelegateBaseItem implements Item {

  private final BaseItemFactory itemFactory;
  private final BaseItem item;
  private final BaseItemToItem converter;

  public BaseItemToItemWrapper(
      BaseItemFactory itemFactory, BaseItemToItem converter, BaseItem item) {
    super(item);
    this.converter = converter;
    this.itemFactory = itemFactory;
    this.item = item;
  }

  @Override
  public Item create() {
    BaseItem created = itemFactory.create(item);
    return converter.convert(created);
  }
}
