/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.util.function.Supplier;

import io.annot8.common.data.content.Table;
import io.annot8.common.data.content.TableContent;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultTableContent extends AbstractContent<Table> implements TableContent {

  private DefaultTableContent(
      String id, String name, ImmutableProperties properties, Supplier<Table> dataSupplier) {
    super(
        Table.class,
        TableContent.class,
        new DefaultAnnotationStore(id),
        id,
        name,
        properties,
        dataSupplier);
  }

  public static class Builder extends AbstractContentBuilder<Table, TableContent> {

    @Override
    protected TableContent create(
        String id, String name, ImmutableProperties properties, Supplier<Table> data) {
      return new DefaultTableContent(id, name, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<Table, TableContent> {

    public BuilderFactory() {
      super(Table.class, TableContent.class);
    }

    @Override
    public Content.Builder<TableContent, Table> create(BaseItem item) {
      return new Builder();
    }
  }
}
