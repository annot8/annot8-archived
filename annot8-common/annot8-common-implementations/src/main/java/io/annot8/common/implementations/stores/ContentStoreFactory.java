/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import io.annot8.core.data.BaseItem;

@FunctionalInterface
public interface ContentStoreFactory {

  ContentStore create(BaseItem item);
}
