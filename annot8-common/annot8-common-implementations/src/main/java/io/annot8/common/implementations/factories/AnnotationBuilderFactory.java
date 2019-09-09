/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.stores.AnnotationStore;

/**
 * Factory to create an annotation builder.
 *
 * <p>Typically used in an AnnotationStore.getBuilder().
 */
@FunctionalInterface
public interface AnnotationBuilderFactory {

  /**
   * Create a new builder for the provided parameters.
   *
   * <p>Most implementation will simply need the store parameter to allow save on save.
   *
   * @param content the content id
   * @param store the annotation store to use
   * @return non-null builder
   */
  Annotation.Builder create(String content, AnnotationStore store);
}
