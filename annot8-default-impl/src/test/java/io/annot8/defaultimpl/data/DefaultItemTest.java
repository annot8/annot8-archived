/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.data;

import io.annot8.core.data.BaseItem;
import io.annot8.defaultimpl.factories.DefaultContentBuilderFactoryRegistry;
import io.annot8.testing.tck.impl.AbstractItemTest;

public class DefaultItemTest extends AbstractItemTest {

  @Override
  protected BaseItem getItem() {
    DefaultContentBuilderFactoryRegistry registry = new DefaultContentBuilderFactoryRegistry(true);
    return new DefaultItem(registry);
  }
}
