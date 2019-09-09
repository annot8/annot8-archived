/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.pipe;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.events.PipeEvent;
import io.annot8.core.data.Item;

public abstract class AbstractItemPipeEvent implements PipeEvent {

  private final Pipe pipe;
  private final Item item;

  public AbstractItemPipeEvent(Pipe pipe, Item item) {
    this.pipe = pipe;
    this.item = item;
  }

  public Item getItem() {
    return item;
  }

  public Pipe getPipe() {
    return pipe;
  }
}
