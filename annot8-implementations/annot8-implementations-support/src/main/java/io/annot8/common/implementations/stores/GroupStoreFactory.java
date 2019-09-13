/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import io.annot8.api.data.Item;
import io.annot8.api.stores.GroupStore;

@FunctionalInterface
public interface GroupStoreFactory {

  GroupStore create(Item item);
}
