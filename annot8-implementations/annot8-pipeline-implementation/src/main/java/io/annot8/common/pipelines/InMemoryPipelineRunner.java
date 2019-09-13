/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.components.logging.Logging;
import io.annot8.common.components.metering.Metering;
import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.implementations.stores.NotifyingItemFactory;
import io.annot8.api.components.Annot8Component;
import io.annot8.api.components.Annot8ComponentDescriptor;
import io.annot8.api.components.Processor;
import io.annot8.api.components.Source;
import io.annot8.api.components.responses.ProcessorResponse;
import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;
import io.annot8.api.pipelines.PipelineDescriptor;

public class InMemoryPipelineRunner implements Runnable {

  private final PipelineDescriptor pipelineDescriptor;
  private final ItemFactory itemFactory;

  private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryPipelineRunner.class);
  private final SimpleContext context;

  private boolean running = true;

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory) {
    this.pipelineDescriptor = pipelineDescriptor;
    this.itemFactory = itemFactory;

    Logging logging = Logging.useLoggerFactory();
    Metering metering = Metering.useGlobalRegistry(pipelineDescriptor.getName());
    this.context = new SimpleContext(logging, metering);
  }

  @Override
  public void run() {
    List<Item> itemsToProcess = new ArrayList<>();

    NotifyingItemFactory nif = new NotifyingItemFactory(itemFactory);
    nif.register(itemsToProcess::add);
    nif.register(i -> LOGGER.debug("Item {} added to queue", i.getId()));

    List<Source> activeSources = new ArrayList<>();
    pipelineDescriptor
        .getSources()
        .stream()
        .map(d -> create(d, Source.class))
        .forEach(activeSources::add);

    List<Processor> activeProcessors = new ArrayList<>();
    pipelineDescriptor
        .getProcessors()
        .stream()
        .map(d -> create(d, Processor.class))
        .forEach(activeProcessors::add);

    while (running && !activeSources.isEmpty()) {
      Iterator<Source> sourceIter = activeSources.iterator();
      while (sourceIter.hasNext()) {
        Source source = sourceIter.next();

        LOGGER.debug(
            "[{}] Reading source {} for new items",
            pipelineDescriptor.getName(),
            source.toString());
        SourceResponse response = source.read(nif);

        switch (response.getStatus()) {
          case DONE:
            LOGGER.info(
                "[{}] Finished reading all items from source {}",
                pipelineDescriptor.getName(),
                source.toString());
            sourceIter.remove();
            break;
          case SOURCE_ERROR:
            LOGGER.error(
                "[{}] Source {} returned a non-recoverable error and has been removed from the pipeline",
                pipelineDescriptor.getName(),
                source.toString());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                LOGGER.error("The following exception was caught by the source", e);
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
        LOGGER.debug(
            "[{}] Beginning processing of item {}", pipelineDescriptor.getName(), item.getId());

        Iterator<Processor> processorIter = activeProcessors.iterator();

        while (processorIter.hasNext()) {
          Processor processor = processorIter.next();

          LOGGER.debug(
              "[{}] Processing item {} using processor {}",
              pipelineDescriptor.getName(),
              item.getId(),
              processor.toString());
          ProcessorResponse response = processor.process(item);

          if (response.getStatus() == ProcessorResponse.Status.ITEM_ERROR) {
            LOGGER.error(
                "[{}] Processor {} returned an error whilst processing the current item {}, and the item will not be processed by the remainder of the pipeline",
                pipelineDescriptor.getName(),
                processor.toString(),
                item.getId());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                LOGGER.error("The following exception was caught by the processor", e);
              }
            }
            break;
          } else if (response.getStatus() == ProcessorResponse.Status.PROCESSOR_ERROR) {
            LOGGER.error(
                "[{}] Processor {} returned a non-recoverable error whilst processing the current item {}, and the processor has been removed from the pipeline",
                pipelineDescriptor.getName(),
                processor.toString(),
                item.getId());
            if (response.hasExceptions()) {
              for (Exception e : response.getExceptions()) {
                LOGGER.error("The following exception was caught by the processor", e);
              }
            }

            processorIter.remove();
          }
        }
      }
    }
  }

  private <T extends Annot8Component> T create(
      Annot8ComponentDescriptor<T, ?> descriptor, Class<T> clazz) {
    return descriptor.create(context);
  }

  public void stop() {
    LOGGER.info("Stopping pipeline after current item/source");
    running = false;
  }
}
