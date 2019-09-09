/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.tck.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.annot8.common.implementations.stores.GroupStoreFactory;
import io.annot8.core.stores.GroupStore;
import io.annot8.testing.testimpl.TestItem;

public abstract class AbstractGroupStoreFactoryTest {

  protected abstract GroupStoreFactory getGroupStoreFactory();

  @Test
  public void testGetGroupStore() {
    GroupStore groupStore = getGroupStoreFactory().create(new TestItem());
    assertNotNull(groupStore);
  }
}
