/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.data;

import io.annot8.core.data.Item;

/**
 * Factory to create new items.
 *
 * <p>The implementation must always be able to create a new item, but it doesn't not need to
 * support linking with child items. In which case create(Item parent) should call create().
 */
public interface ItemFactory {

  /**
   * Create a new item
   *
   * @return non-null
   */
  Item create();

  /**
   * Create a new item for a parent
   *
   * @return non-null
   */
  Item create(Item parent);
}
