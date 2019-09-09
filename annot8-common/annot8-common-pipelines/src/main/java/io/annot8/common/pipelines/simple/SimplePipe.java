/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.configuration.ComponentConfigurer;
import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.base.AbstractPipe;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.events.pipe.AfterItemProcessedPipeEvent;
import io.annot8.common.pipelines.events.pipe.BeforeItemProcessedPipeEvent;
import io.annot8.common.pipelines.events.pipe.ItemDiscardedPipeEvent;
import io.annot8.common.pipelines.events.pipe.ItemEnteredPipeEvent;
import io.annot8.common.pipelines.events.pipe.ItemExitedPipeEvent;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

public class SimplePipe extends AbstractPipe implements Pipe {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipe.class);

  private final ResourcesHolder resourcesHolder;
  private final ComponentHolder<Processor> processorHolder;

  private List<Processor> processors;

  public SimplePipe(ResourcesHolder resourcesHolder, ComponentHolder<Processor> processorHolder) {
    this("anonymous", resourcesHolder, processorHolder);
  }

  public SimplePipe(
      String name, ResourcesHolder resourcesHolder, ComponentHolder<Processor> processorHolder) {
    super(name);
    this.resourcesHolder = resourcesHolder;
    this.processorHolder = processorHolder;
  }

  @Override
  public void configure(Context context) {
    close();

    ComponentConfigurer componentConfigurer = new ComponentConfigurer(context);
    componentConfigurer.configureResources(resourcesHolder);
    processors = componentConfigurer.configureComponents(processorHolder);
  }

  @Override
  public ProcessorResponse process(final Item item) {

    fire(new ItemEnteredPipeEvent(this, item));

    for (final Processor processor : processors) {

      fire(new BeforeItemProcessedPipeEvent(this, item, processor));

      try {
        final ProcessorResponse response = processor.process(item);
        fire(new AfterItemProcessedPipeEvent(this, item, processor, response));

        final Status status = response.getStatus();
        if (status == Status.OK) {
          if (item.isDiscarded()) {
            LOGGER.warn("Item discarded, stopping processing");
            fire(new ItemDiscardedPipeEvent(this, item));
            return response;
          }

        } else if (status == Status.PROCESSOR_ERROR) {
          LOGGER.error("RunnablePipeline problem, exiting");
          return response;
        } else if (status == Status.ITEM_ERROR) {
          fire(new ItemEnteredPipeEvent(this, item));
          LOGGER.error("Item problem, skipping rest of pipeline");
          return response;
        }

      } catch (final Annot8Exception e) {
        LOGGER.error(
            "Failed to process data item with processor {}", processor.getClass().getName(), e);
      }
    }

    fire(new ItemExitedPipeEvent(this, item));

    return ProcessorResponse.ok();
  }

  @Override
  public void close() {
    if (processors != null) {
      processors.forEach(Processor::close);
      processors.clear();
      processors = null;
    }
  }
}
