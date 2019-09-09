/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import java.util.function.Consumer;

import io.annot8.core.data.BaseItem;

public interface BaseItemQueue extends Consumer<BaseItem> {

  void add(BaseItem item);

  @Override
  default void accept(BaseItem item) {
    add(item);
  }

  boolean hasItems();

  BaseItem next();
}
