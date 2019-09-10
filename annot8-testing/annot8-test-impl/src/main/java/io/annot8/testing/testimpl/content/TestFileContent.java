/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import java.io.File;
import java.util.function.Supplier;

import io.annot8.common.data.content.FileContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.AbstractTestContent;
import io.annot8.testing.testimpl.TestAnnotationStoreFactory;

public class TestFileContent extends AbstractTestContent<File> implements FileContent {

  public TestFileContent() {
    super(File.class);
    setData(new File("."));
  }

  public TestFileContent(
      String id, String description, ImmutableProperties properties, Supplier<File> data) {
    super(File.class, id, description, properties, data);
  }

  public TestFileContent(
      AnnotationStore annotations,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<File> data) {
    super(File.class, c -> annotations, id, description, properties, data);
  }

  public TestFileContent(
      AnnotationStoreFactory annotationStore,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<File> data) {
    super(File.class, annotationStore, id, description, properties, data);
  }

  @Override
  public Class<? extends Content<File>> getContentClass() {
    return FileContent.class;
  }

  public static class Builder extends AbstractContentBuilder<File, TestFileContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public Builder(AnnotationStoreFactory annotationStoreFactory) {
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    protected TestFileContent create(
        String id, String description, ImmutableProperties properties, Supplier<File> data) {
      return new TestFileContent(annotationStoreFactory, id, description, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<File, TestFileContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public BuilderFactory() {
      this(TestAnnotationStoreFactory.getInstance());
    }

    public BuilderFactory(AnnotationStoreFactory annotationStoreFactory) {
      super(File.class, TestFileContent.class);
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    public TestFileContent.Builder create(BaseItem item) {
      return new TestFileContent.Builder(annotationStoreFactory);
    }
  }
}
