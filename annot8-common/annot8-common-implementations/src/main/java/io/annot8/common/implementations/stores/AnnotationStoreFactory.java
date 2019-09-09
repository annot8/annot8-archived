/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import io.annot8.core.data.Content;
import io.annot8.core.stores.AnnotationStore;

@FunctionalInterface
public interface AnnotationStoreFactory {

  AnnotationStore create(Content<?> content);
}
