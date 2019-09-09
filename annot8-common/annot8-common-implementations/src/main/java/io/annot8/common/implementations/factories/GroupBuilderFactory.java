/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import io.annot8.core.annotations.Group;
import io.annot8.core.data.BaseItem;
import io.annot8.core.stores.GroupStore;

/**
 * Factory to create an group builder.
 *
 * <p>Typically used in a GroupStore.getBuilder().
 */
@FunctionalInterface
public interface GroupBuilderFactory<T> {

  /**
   * Create a new builder for the provided item.
   *
   * @param item the item owning this content
   * @param groupStore the group store
   * @return non-null
   */
  Group.Builder create(BaseItem item, GroupStore groupStore);
}
