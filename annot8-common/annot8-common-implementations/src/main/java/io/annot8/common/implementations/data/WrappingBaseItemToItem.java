/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.data;

import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Item;

public class WrappingBaseItemToItem implements BaseItemToItem {

  private final BaseItemFactory itemFactory;

  public WrappingBaseItemToItem(BaseItemFactory itemFactory) {
    this.itemFactory = itemFactory;
  }

  @Override
  public Item convert(BaseItem item) {
    if (item instanceof BaseItemToItemWrapper) {
      return (Item) item;
    }
    return new BaseItemToItemWrapper(itemFactory, this, item);
  }
}
