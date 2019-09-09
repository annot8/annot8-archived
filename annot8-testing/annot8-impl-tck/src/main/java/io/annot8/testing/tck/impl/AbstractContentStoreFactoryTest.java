/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.tck.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.annot8.common.implementations.stores.ContentStore;
import io.annot8.common.implementations.stores.ContentStoreFactory;
import io.annot8.testing.testimpl.TestItem;

public abstract class AbstractContentStoreFactoryTest {

  protected abstract ContentStoreFactory getContentStoreFactory();

  @Test
  public void testGetContentStore() {
    TestItem item = new TestItem();
    ContentStore contentStore = getContentStoreFactory().create(item);
    assertNotNull(contentStore);
  }
}
