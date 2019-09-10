/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.util.function.Supplier;

import io.annot8.common.data.content.Table;
import io.annot8.common.data.content.TableContent;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultTableContent extends AbstractContent<Table> implements TableContent {

  private DefaultTableContent(
          Item item,
      String id, String description, ImmutableProperties properties, Supplier<Table> dataSupplier) {
    super(
            item,
        Table.class,
        TableContent.class,
        DefaultAnnotationStore::new,
        id,
        description,
        properties,
        dataSupplier);
  }

  public static class Builder extends AbstractContentBuilder<Table, TableContent> {

      public Builder(Item item) {
          super(item);
      }

      @Override
    protected TableContent create(
        String id, String description, ImmutableProperties properties, Supplier<Table> data) {
      return new DefaultTableContent(getItem(), id, description, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<Table, TableContent> {

    public BuilderFactory() {
      super(Table.class, TableContent.class);
    }

    @Override
    public Content.Builder<TableContent, Table> create(Item item) {
      return new Builder(item);
    }
  }
}
