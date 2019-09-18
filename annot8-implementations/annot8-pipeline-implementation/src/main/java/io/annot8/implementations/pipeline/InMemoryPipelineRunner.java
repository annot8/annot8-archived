/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.pipeline;

import io.annot8.api.components.Annot8Component;
import io.annot8.api.components.Annot8ComponentDescriptor;
import io.annot8.api.components.Processor;
import io.annot8.api.components.Source;
import io.annot8.api.components.responses.ProcessorResponse;
import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.context.Context;
import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;
import io.annot8.api.pipelines.PipelineDescriptor;
import io.annot8.api.pipelines.PipelineRunner;
import io.annot8.common.components.logging.Logging;
import io.annot8.common.components.metering.Metering;
import io.annot8.implementations.support.context.SimpleContext;
import io.annot8.implementations.support.stores.NotifyingItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class InMemoryPipelineRunner implements PipelineRunner {



  private final Context context;
  private final Logger logger;

  private boolean running = true;

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory) {
    this.pipelineDescriptor = pipelineDescriptor;
    this.itemFactory = itemFactory;


  }

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory, Logging logging, Metering metering) {
    this.pipelineDescriptor = pipelineDescriptor;
    this.itemFactory = itemFactory;
    this.context = new SimpleContext(logging, metering);
    this.logger = logging.getLogger(InMemoryPipelineRunner.class);
  }

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory, Context context) {
    this.pipelineDescriptor = pipelineDescriptor;
    this.itemFactory = itemFactory;
    this.context = context;

    Optional<Logging> logging = context.getResource(Logging.class);
    if(logging.isPresent()) {
      this.logger = logging.get().getLogger(InMemoryPipelineRunner.class);
    }else{
      this.logger = LoggerFactory.getLogger(InMemoryPipelineRunner.class);
    }
  }


  @Override
  public void run() {


    while (running && !activeSources.isEmpty()) {
      Iterator<Source> sourceIter = activeSources.iterator();
      while (sourceIter.hasNext()) {
        Source source = sourceIter.next();

        logger.debug(
            "[{}] Reading source {} for new items",
            pipelineDescriptor.getName(),
            source.toString());
        SourceResponse response = source.read(nif);

        switch (response.getStatus()) {
          case DONE:
            logger.info(
                "[{}] Finished reading all items from source {}",
                pipelineDescriptor.getName(),
                source.toString());
            sourceIter.remove();
            break;
          case SOURCE_ERROR:
            logger.error(
                "[{}] Source {} returned a non-recoverable error and has been removed from the pipeline",
                pipelineDescriptor.getName(),
                source.toString());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                logger.error("The following exception was caught by the source", e);
              }
            }

            sourceIter.remove();
            break;
        }

        if (!running) break;
      }

      // Process current items
      while (running && !itemsToProcess.isEmpty()) {
        Item item = itemsToProcess.remove(0);
        logger.debug(
            "[{}] Beginning processing of item {}", pipelineDescriptor.getName(), item.getId());

        Iterator<Processor> processorIter = activeProcessors.iterator();

        while (processorIter.hasNext()) {
          Processor processor = processorIter.next();

          logger.debug(
              "[{}] Processing item {} using processor {}",
              pipelineDescriptor.getName(),
              item.getId(),
              processor.toString());
          ProcessorResponse response = processor.process(item);

          if (response.getStatus() == ProcessorResponse.Status.ITEM_ERROR) {
            logger.error(
                "[{}] Processor {} returned an error whilst processing the current item {}, and the item will not be processed by the remainder of the pipeline",
                pipelineDescriptor.getName(),
                processor.toString(),
                item.getId());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                logger.error("The following exception was caught by the processor", e);
              }
            }
            break;
          } else if (response.getStatus() == ProcessorResponse.Status.PROCESSOR_ERROR) {
            logger.error(
                "[{}] Processor {} returned a non-recoverable error whilst processing the current item {}, and the processor has been removed from the pipeline",
                pipelineDescriptor.getName(),
                processor.toString(),
                item.getId());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                logger.error("The following exception was caught by the processor", e);
              }
            }

            processorIter.remove();
          }
        }
      }
    }
  }



  public void stop() {
    logger.info("Stopping pipeline after current item/source");
    running = false;
  }
}
