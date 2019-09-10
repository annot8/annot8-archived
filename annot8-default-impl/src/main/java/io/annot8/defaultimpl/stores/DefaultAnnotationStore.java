/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.stores;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.annot8.common.implementations.delegates.DelegateAnnotationBuilder;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.defaultimpl.annotations.DefaultAnnotation;

/** In memory implementation, backed by a HashMap, of AnnotationStore */
public class DefaultAnnotationStore implements AnnotationStore {

  private final Map<String, Annotation> annotations = new ConcurrentHashMap<>();
  private final Content<?> content;

  /**
   * Construct a new instance of this class using DefaultAnnotation.AbstractContentBuilder as the
   * annotation builder
   */
  public DefaultAnnotationStore(Content<?> content) {
    this.content = content;
  }

  @Override
  public Content<?> getContent() {
    return content;
  }

  @Override
  public Annotation.Builder getBuilder() {
    return new DelegateAnnotationBuilder(new DefaultAnnotation.Builder(content.getId())) {
      @Override
      public Annotation save() throws IncompleteException {
        return DefaultAnnotationStore.this.save(super.save());
      }
    };
  }

  private Annotation save(Annotation annotation) {
    annotations.put(annotation.getId(), annotation);
    return annotation;
  }

  @Override
  public void delete(Annotation annotation) {
    annotations.remove(annotation.getId(), annotation);
  }

  @Override
  public Stream<Annotation> getAll() {
    return annotations.values().stream();
  }

  @Override
  public Optional<Annotation> getById(String s) {
    return Optional.ofNullable(annotations.get(s));
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [annotations=" + annotations.size() + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotations);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof AnnotationStore)) {
      return false;
    }

    AnnotationStore as = (AnnotationStore) obj;

    Set<Annotation> allAnnotations = as.getAll().collect(Collectors.toSet());

    return Objects.equals(new HashSet<>(annotations.values()), allAnnotations);
  }
}
