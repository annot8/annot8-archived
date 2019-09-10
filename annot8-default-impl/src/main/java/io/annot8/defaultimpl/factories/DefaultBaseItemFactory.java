/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.factories;

import java.util.Objects;

import io.annot8.common.implementations.registries.ContentBuilderFactoryRegistry;
import io.annot8.defaultimpl.data.DefaultItem;

public class DefaultBaseItemFactory implements BaseItemFactory {

  private final ContentBuilderFactoryRegistry contentBuilderFactoryRegistry;

  public DefaultBaseItemFactory(ContentBuilderFactoryRegistry contentBuilderFactoryRegistry) {
    this.contentBuilderFactoryRegistry = contentBuilderFactoryRegistry;
  }

  @Override
  public BaseItem create() {
    return new DefaultItem(contentBuilderFactoryRegistry);
  }

  @Override
  public BaseItem create(BaseItem parent) {
    Objects.requireNonNull(parent);
    return new DefaultItem(parent.getId(), contentBuilderFactoryRegistry);
  }
}
