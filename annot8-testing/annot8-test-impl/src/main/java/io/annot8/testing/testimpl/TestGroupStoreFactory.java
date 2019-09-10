/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import io.annot8.common.implementations.stores.GroupStoreFactory;
import io.annot8.core.stores.GroupStore;

public class TestGroupStoreFactory implements GroupStoreFactory {

  @Override
  public GroupStore create(BaseItem item) {
    return new TestGroupStore(item);
  }
}
