/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.registries;

import java.util.Optional;

import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.core.data.Content;

/** A registry of content builder factories. */
public interface ContentBuilderFactoryRegistry {

  /**
   * Get the (best) content builder factory for the content class requested, if available.
   *
   * @param contentClass the content type required
   * @return builder (if available for that content class)
   */
  <D, C extends Content<D>> Optional<ContentBuilderFactory<D, C>> get(Class<C> contentClass);

  <D, C extends Content<D>, I extends C> void register(
      Class<C> contentClass, ContentBuilderFactory<D, I> contentBuilderFactory);
}
