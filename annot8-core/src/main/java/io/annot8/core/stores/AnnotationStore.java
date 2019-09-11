/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.stores;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;

/** Base annotations interface from which all other annotation stores extend. */
public interface AnnotationStore {

  /**
   * Get the content which this store holds annotations for.
   *
   * @return content
   */
  Content<?> getContent();

  /**
   * Get a new annotation builder
   *
   * @return a builder object for the supported annotations
   */
  Annotation.Builder getBuilder();

  /**
   * Get a builder to createContent a new annotation
   *
   * @return a builder to createContent a new annotation
   */
  default Annotation.Builder create() {
    return getBuilder();
  }

  /**
   * Clone an existing annotation to createContent a new annotation
   *
   * @param existing the annotation to copy
   * @return a builder to based on the an existing annotation, but don't overwrite that annotation
   *     on save.
   */
  default Annotation.Builder copy(Annotation existing) {
    return getBuilder().from(existing).newId();
  }

  /**
   * Edit an annotation (saving will replace the old version)
   *
   * @param existing the annotation to edit
   * @return a builder to edit an existing annotation
   */
  default Annotation.Builder edit(Annotation existing) {
    return getBuilder().from(existing);
  }

  /**
   * Delete an annotation from the store
   *
   * @param annotation the annotation to delete
   */
  void delete(final Annotation annotation);

  /**
   * Delete a collection of annotations from the store
   *
   * @param annotations the annotations to delete
   */
  default void delete(final Collection<Annotation> annotations) {
    annotations.forEach(this::delete);
  }

  /** Delete all annotations from the store */
  default void deleteAll() {
    delete(getAll().collect(Collectors.toList()));
  }

  /**
   * Get all annotations currently held in this store
   *
   * @return annotations
   */
  Stream<Annotation> getAll();

  /**
   * Get all annotations of a given type currently held in this store
   *
   * @param type the annotation type to find
   * @return annotations
   */
  default Stream<Annotation> getByType(final String type) {
    return getAll().filter(a -> type.equals(a.getType()));
  }

  /**
   * Get all annotations of a given bounds currently held in this store
   *
   * @param boundsClass the bound to filter on
   * @return annotations
   */
  default <B extends Bounds> Stream<Annotation> getByBounds(final Class<B> boundsClass) {
    return getAll().filter(a -> a.getBounds() != null && boundsClass.isInstance(a.getBounds()));
  }

  /**
   * Get all annotations of a given bounds and type currently held in this store
   *
   * @param boundsClass the bound to filter on
   * @param type the annotation type to find
   * @return annotations
   */
  default <B extends Bounds> Stream<Annotation> getByBoundsAndType(
      final Class<B> boundsClass, final String type) {
    return getByBounds(boundsClass).filter(a -> type.equals(a.getType()));
  }

  /**
   * Get the annotation with the given ID, if it is currently held in this store
   *
   * @param annotationId the id
   * @return annotation
   */
  Optional<Annotation> getById(final String annotationId);
}
