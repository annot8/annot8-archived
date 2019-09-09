/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.pipe;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

public class AfterItemProcessedPipeEvent extends AbstractItemPipeEvent {

  private final Processor processor;
  private final ProcessorResponse response;

  public AfterItemProcessedPipeEvent(
      Pipe pipe, Item item, Processor processor, ProcessorResponse response) {
    super(pipe, item);
    this.processor = processor;
    this.response = response;
  }

  public Processor getProcessor() {
    return processor;
  }

  public ProcessorResponse getResponse() {
    return response;
  }
}
