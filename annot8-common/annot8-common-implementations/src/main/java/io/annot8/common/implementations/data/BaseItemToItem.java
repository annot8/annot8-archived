/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.data;

import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Item;

@FunctionalInterface
public interface BaseItemToItem {

  Item convert(BaseItem item);
}
