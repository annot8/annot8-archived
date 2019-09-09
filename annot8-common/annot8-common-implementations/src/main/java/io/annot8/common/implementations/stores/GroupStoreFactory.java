/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import io.annot8.core.data.BaseItem;
import io.annot8.core.stores.GroupStore;

@FunctionalInterface
public interface GroupStoreFactory {

  GroupStore create(BaseItem item);
}
