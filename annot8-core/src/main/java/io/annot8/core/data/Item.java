/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.data;

/** Item interface used by components. */
public interface Item extends BaseItem {

  /**
   * Create a child item of this item, which will be processed independently
   *
   * @return new item, with this item as its parent
   */
  Item create();
}
