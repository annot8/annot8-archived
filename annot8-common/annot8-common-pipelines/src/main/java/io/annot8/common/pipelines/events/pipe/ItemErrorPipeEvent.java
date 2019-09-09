/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.pipe;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.data.Item;

public class ItemErrorPipeEvent extends AbstractItemPipeEvent {

  public ItemErrorPipeEvent(Pipe pipe, Item item) {
    super(pipe, item);
  }
}
