/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import io.annot8.common.data.content.InputStreamContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.AbstractTestContent;
import io.annot8.testing.testimpl.TestAnnotationStoreFactory;

public class TestInputStreamContent extends AbstractTestContent<InputStream>
    implements InputStreamContent {

  private static final byte[] DEFAULT_DATA = "Test Data".getBytes(StandardCharsets.UTF_8);

  public TestInputStreamContent() {
    super(InputStream.class);
    // THis is not really useful in general, but its something non-null
    setData(() -> new ByteArrayInputStream(DEFAULT_DATA));
  }

  public TestInputStreamContent(
      String id, String description, ImmutableProperties properties, Supplier<InputStream> data) {
    super(InputStream.class, id, description, properties, data);
  }

  public TestInputStreamContent(
      AnnotationStore annotations,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<InputStream> data) {
    super(InputStream.class, c -> annotations, id, description, properties, data);
  }

  public TestInputStreamContent(
      AnnotationStoreFactory annotationStore,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<InputStream> data) {
    super(InputStream.class, annotationStore, id, description, properties, data);
  }

  @Override
  public Class<? extends Content<InputStream>> getContentClass() {
    return InputStreamContent.class;
  }

  public static class Builder extends AbstractContentBuilder<InputStream, TestInputStreamContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public Builder(AnnotationStoreFactory annotationStoreFactory) {
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    protected TestInputStreamContent create(
        String id, String description, ImmutableProperties properties, Supplier<InputStream> data) {
      return new TestInputStreamContent(annotationStoreFactory, id, description, properties, data);
    }
  }

  public static class BuilderFactory
      extends AbstractContentBuilderFactory<InputStream, TestInputStreamContent> {

    private final AnnotationStoreFactory annotationStoreFactory;

    public BuilderFactory() {
      this(TestAnnotationStoreFactory.getInstance());
    }

    public BuilderFactory(AnnotationStoreFactory annotationStoreFactory) {
      super(InputStream.class, TestInputStreamContent.class);
      this.annotationStoreFactory = annotationStoreFactory;
    }

    @Override
    public TestInputStreamContent.Builder create(BaseItem item) {
      return new TestInputStreamContent.Builder(annotationStoreFactory);
    }
  }
}
