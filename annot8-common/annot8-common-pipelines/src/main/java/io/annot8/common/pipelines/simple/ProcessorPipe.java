/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.base.AbstractPipe;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class ProcessorPipe extends AbstractPipe {

  private final Processor processor;

  public ProcessorPipe(Processor processor) {
    super(processor.toString());
    this.processor = processor;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    processor.configure(context);
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    return processor.process(item);
  }

  @Override
  public void close() {
    processor.close();
  }
}
