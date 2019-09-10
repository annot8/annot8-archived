/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import java.util.function.Supplier;

import io.annot8.common.data.content.Text;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.AbstractTestContent;
import io.annot8.testing.testimpl.TestAnnotationStoreFactory;

public class TestStringContent extends AbstractTestContent<String> implements Text {

  public TestStringContent() {
    super(String.class);
    setData("Test data");
  }

  public TestStringContent(
      String id, String description, ImmutableProperties properties, Supplier<String> data) {
    super(String.class, id, description, properties, data);
  }

  public TestStringContent(
      AnnotationStore annotations,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<String> data) {
    super(String.class, c -> annotations, id, description, properties, data);
  }

  public TestStringContent(
      AnnotationStoreFactory annotationStore,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<String> data) {
    super(String.class, annotationStore, id, description, properties, data);
  }

  @Override
  public Class<? extends Content<String>> getContentClass() {
    return Text.class;
  }

  public static class Builder extends AbstractContentBuilder<String, TestStringContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public Builder(AnnotationStoreFactory annotationStoreFactory) {
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    protected TestStringContent create(
        String id, String description, ImmutableProperties properties, Supplier<String> data) {
      return new TestStringContent(annotationStoreFactory, id, description, properties, data);
    }
  }

  public static class BuilderFactory
      extends AbstractContentBuilderFactory<String, TestStringContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public BuilderFactory() {
      this(TestAnnotationStoreFactory.getInstance());
    }

    public BuilderFactory(AnnotationStoreFactory annotationStoreFactory) {
      super(String.class, TestStringContent.class);
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    public Builder create(BaseItem item) {
      return new Builder(annotationStoreFactory);
    }
  }
}
