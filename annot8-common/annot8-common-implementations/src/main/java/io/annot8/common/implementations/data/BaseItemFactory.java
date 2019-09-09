/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.data;

import io.annot8.core.data.BaseItem;

/**
 * Factory to create new items.
 *
 * <p>The implementation must always be able to create a new item, but it doesn't not need to
 * support linking with child items.
 */
@FunctionalInterface
public interface BaseItemFactory {

  /**
   * Create a new item
   *
   * @return non-null
   */
  BaseItem create();

  /**
   * Create a new item, linked to parent
   *
   * @param parent parent item
   * @return new item
   */
  default BaseItem create(BaseItem parent) {
    return create();
  }
}
