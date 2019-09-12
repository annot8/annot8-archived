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
  private Content<?> content;

  public TestAnnotationStore(Content<?> content) {
    this(content, TestAnnotationBuilder.factory());
  }

  public TestAnnotationStore(
      Content<?> content, AnnotationBuilderFactory annotationBuilderFactory) {
    this.content = content;
    this.annotationBuilderFactory = annotationBuilderFactory;
  }

  @Override
  public Content<?> getContent() {
    return content;
  }

  public void setContent(Content<?> content) {
    this.content = content;
  }

  @Override
  public Builder getBuilder() {
    return new DelegateAnnotationBuilder(annotationBuilderFactory.create(content, this)) {
      @Override
      public Annotation save() throws IncompleteException {
        return TestAnnotationStore.this.save(super.save());
      }
    };
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
