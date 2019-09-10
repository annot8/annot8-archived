/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.io.InputStream;
import java.util.function.Supplier;

import io.annot8.common.data.content.InputStreamContent;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.Annot8RuntimeException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultInputStream extends AbstractContent<InputStream> implements InputStreamContent {

  private DefaultInputStream(
      String id, String description, ImmutableProperties properties, Supplier<InputStream> data) {
    super(
        InputStream.class,
        InputStreamContent.class,
        new DefaultAnnotationStore(id),
        id,
        description,
        properties,
        data);
  }

  public static class Builder extends AbstractContentBuilder<InputStream, DefaultInputStream> {

    @Override
    public Content.Builder<DefaultInputStream, InputStream> withData(InputStream data) {
      throw new Annot8RuntimeException(
          "Must use a Supplier to provider InputStream, otherwise it can only be read once");
    }

    @Override
    protected DefaultInputStream create(
        String id, String description, ImmutableProperties properties, Supplier<InputStream> data) {
      return new DefaultInputStream(id, description, properties, data);
    }
  }

  public static class BuilderFactory
      extends AbstractContentBuilderFactory<InputStream, DefaultInputStream> {

    public BuilderFactory() {
      super(InputStream.class, DefaultInputStream.class);
    }

    @Override
    public Content.Builder<DefaultInputStream, InputStream> create(BaseItem item) {
      return new DefaultInputStream.Builder();
    }
  }
}
