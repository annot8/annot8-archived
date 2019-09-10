/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;
import io.annot8.core.stores.AnnotationStore;

public class TestAnnotationStoreFactory implements AnnotationStoreFactory {

  private static final TestAnnotationStoreFactory INSTANCE = new TestAnnotationStoreFactory();

  public static AnnotationStoreFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public AnnotationStore create(Content<?> content) {
    return new TestAnnotationStore(content);
  }
}
