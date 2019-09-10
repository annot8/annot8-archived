/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.LinkedList;
import java.util.List;

public class TestBaseItemFactory implements BaseItemFactory {

  private List<BaseItem> createdItems = new LinkedList<>();

  @Override
  public BaseItem create() {
    TestItem i = new TestItem();
    createdItems.add(i);
    return i;
  }

  @Override
  public BaseItem create(BaseItem parent) {
    TestItem i = new TestItem(parent.getId());
    createdItems.add(i);
    return i;
  }

  public List<BaseItem> getCreatedItems() {
    return createdItems;
  }
}
