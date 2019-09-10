/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import java.util.function.Supplier;

import io.annot8.common.data.content.Table;
import io.annot8.common.data.content.TableContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.testing.testimpl.AbstractTestContent;

public class TestTableContent extends AbstractTestContent<Table> implements TableContent {

  public TestTableContent() {
    super(Table.class);
  }

  public TestTableContent(String description) {
    super(Table.class, description);
  }

  public TestTableContent(
      Class<Table> dataClass, String id, String description, ImmutableProperties properties) {
    super(Table.class, id, description, properties);
  }

  public TestTableContent(
      String id, String description, ImmutableProperties properties, Supplier<Table> data) {
    super(Table.class, id, description, properties, data);
  }

  public TestTableContent(
      AnnotationStoreFactory annotationStoreFactory,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<Table> data) {
    super(Table.class, annotationStoreFactory, id, description, properties, data);
  }

  public TestTableContent(
      Class<Table> dataClass, String id, String description, ImmutableProperties properties, Table data) {
    super(dataClass, id, description, properties, data);
  }

  @Override
  public Class<? extends Content<Table>> getContentClass() {
    return TableContent.class;
  }

  public static class Builder extends AbstractContentBuilder<Table, TableContent> {

    @Override
    protected TableContent create(
        String id, String description, ImmutableProperties properties, Supplier<Table> data) {
      return new TestTableContent(id, description, properties, data);
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
