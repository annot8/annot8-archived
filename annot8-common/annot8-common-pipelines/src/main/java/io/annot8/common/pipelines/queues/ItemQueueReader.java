/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.core.data.Item;

public interface ItemQueueReader {

  boolean hasItems();

  Item next();
}
