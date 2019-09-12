/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.net.URI;
import java.util.function.Supplier;

import io.annot8.common.data.content.UriContent;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultUri extends AbstractContent<URI> implements UriContent {

  private DefaultUri(
      Item item,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<URI> data) {
    super(
        item,
        URI.class,
        UriContent.class,
        DefaultAnnotationStore::new,
        id,
        description,
        properties,
        data);
  }

  public static class Builder extends AbstractContentBuilder<URI, UriContent> {

    public Builder(Item item) {
      super(item);
    }

    @Override
    protected UriContent create(
        String id, String description, ImmutableProperties properties, Supplier<URI> data) {
      return new DefaultUri(getItem(), id, description, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<URI, UriContent> {

    public BuilderFactory() {
      super(URI.class, UriContent.class);
    }

    @Override
    public Content.Builder<UriContent, URI> create(Item item) {
      return new Builder(item);
    }
  }
}
