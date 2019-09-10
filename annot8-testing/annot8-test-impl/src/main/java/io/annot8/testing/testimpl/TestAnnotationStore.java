/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import io.annot8.common.implementations.delegates.DelegateAnnotationBuilder;
import io.annot8.common.implementations.factories.AnnotationBuilderFactory;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Annotation.Builder;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.stores.AnnotationStore;

public class TestAnnotationStore implements AnnotationStore {

  private final Map<String, Annotation> annotations = new ConcurrentHashMap<>();
  private final AnnotationBuilderFactory annotationBuilderFactory;
  private String contentId;

  public TestAnnotationStore() {
    this(TestConstants.CONTENT_ID);
  }

  public TestAnnotationStore(String contentId) {
    this(contentId, TestAnnotationBuilder.factory());
  }

  public TestAnnotationStore(String contentId, AnnotationBuilderFactory annotationBuilderFactory) {
    this.contentId = contentId;
    this.annotationBuilderFactory = annotationBuilderFactory;
  }

  public TestAnnotationStore(Content<?> content) {
    this(content.getId());
  }

  @Override
  public Builder getBuilder() {
    return new DelegateAnnotationBuilder(annotationBuilderFactory.create(contentId, this)) {
      @Override
      public Annotation save() throws IncompleteException {
        return TestAnnotationStore.this.save(super.save());
      }
    };
  }

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }

  public Annotation save(Builder annotationBuilder) throws IncompleteException {
    Annotation annotation = annotationBuilder.save();
    return save(annotation);
  }

  public Annotation save(Annotation annotation) {
    annotations.put(annotation.getId(), annotation);
    return annotation;
  }

  @Override
  public void delete(Annotation annotation) {
    annotations.remove(annotation.getId());
  }

  @Override
  public Stream<Annotation> getAll() {
    return annotations.values().stream();
  }

  @Override
  public Optional<Annotation> getById(String annotationId) {
    return Optional.ofNullable(annotations.get(annotationId));
  }
}
