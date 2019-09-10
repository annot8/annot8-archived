/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.io.File;
import java.util.function.Supplier;

import io.annot8.common.data.content.FileContent;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultFile extends AbstractContent<File> implements FileContent {

  private DefaultFile(Item item, String id, String description, ImmutableProperties properties, Supplier<File> data) {
    super(
        item, File.class, FileContent.class, DefaultAnnotationStore::new, id, description, properties, data);
  }

  public static class Builder extends AbstractContentBuilder<File, DefaultFile> {

      public Builder(Item item) {
          super(item);
      }

      @Override
    protected DefaultFile create(
        String id, String description, ImmutableProperties properties, Supplier<File> data) {
      return new DefaultFile(getItem(), id, description, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<File, DefaultFile> {

    public BuilderFactory() {
      super(File.class, DefaultFile.class);
    }

    @Override
    public Content.Builder<DefaultFile, File> create(Item item) {
      return new DefaultFile.Builder(item);
    }
  }
}
