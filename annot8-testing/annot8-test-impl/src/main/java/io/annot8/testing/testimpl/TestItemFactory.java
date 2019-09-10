/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.LinkedList;
import java.util.List;

import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;

public class TestItemFactory implements ItemFactory {

  private List<Item> createdItems = new LinkedList<>();

  @Override
  public Item create() {
    Item item = new TestItem();
    createdItems.add(item);
    return item;
  }

  @Override
  public Item create(Item parent) {
    TestItem i = new TestItem(parent.getId());
    createdItems.add(i);
    return i;
  }

  public List<Item> getCreatedItems() {
    return createdItems;
  }
}
