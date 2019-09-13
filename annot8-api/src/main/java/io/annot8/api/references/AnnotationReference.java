/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.references;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.api.annotations.Annotation;

/**
 * A reference to an annotation.
 *
 * <p>As annotations can change (though their id and content stays the same) we can't simply hold
 * Annotation in Java objects. Instead we hold a reference to them (based on content and annotation
 * ids).
 *
 * <p>Since an content or annotation may be deleted, we do not know if the annotation still exists
 * when we call toAnnotation.
 */
public interface AnnotationReference {

  /**
   * Convert a stream of annotation references to annotations
   *
   * @param references reference to convert (may be null)
   * @return annotations
   */
  static Stream<Annotation> toAnnotations(Stream<AnnotationReference> references) {
    if (references == null) {
      return Stream.empty();
    }

    return references
        .map(AnnotationReference::toAnnotation)
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  /**
   * The id of the content to which this annotation belongs.
   *
   * @return the content id
   */
  String getContentId();

  /**
   * The annotation's id
   *
   * @return the id
   */
  String getAnnotationId();

  /**
   * Convert the reference to an annotations.
   *
   * <p>Since the underlying content or annotation may have been deleted this returns an optional.
   *
   * @return the annotation
   */
  Optional<Annotation> toAnnotation();
}
