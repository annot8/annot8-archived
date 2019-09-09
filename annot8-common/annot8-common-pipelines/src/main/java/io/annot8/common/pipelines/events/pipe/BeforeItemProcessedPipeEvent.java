/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.pipe;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.Processor;
import io.annot8.core.data.Item;

public class BeforeItemProcessedPipeEvent extends AbstractItemPipeEvent {

  private final Processor processor;

  public BeforeItemProcessedPipeEvent(Pipe pipe, Item item, Processor processor) {
    super(pipe, item);
    this.processor = processor;
  }

  public Processor getProcessor() {
    return processor;
  }
}
