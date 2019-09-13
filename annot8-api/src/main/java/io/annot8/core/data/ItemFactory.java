/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.data;

/**
 * Factory to createContent new items.
 *
 * <p>The implementation must always be able to createContent a new item, but it doesn't need to
 * processor linking with child items. In which case createContent(Item parent) should call
 * createContent().
 */
public interface ItemFactory {

  /**
   * Create a new item
   *
   * @return non-null
   */
  Item create();

  /**
   * Create a new item with a parent
   *
   * @return non-null
   */
  Item create(Item parent);
}
