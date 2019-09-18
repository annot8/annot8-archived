/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.pipeline;

import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;
import io.annot8.api.pipelines.PipelineDescriptor;
import io.annot8.api.pipelines.PipelineRunner;
import io.annot8.common.components.logging.Logging;
import io.annot8.implementations.support.stores.NotifyingItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPipelineRunner implements PipelineRunner {

  private final Pipeline pipeline;
  private final Logger logger;
  private final NotifyingItemFactory itemFactory;
  private final List<Item> itemsToProcess;

  private boolean running = true;


  public InMemoryPipelineRunner(Pipeline pipeline, ItemFactory itemFactory) {
    this.pipeline = pipeline;
    this.logger = pipeline.getContext().getResource(Logging.class)
            .map(l -> l.getLogger(InMemoryPipelineRunner.class))
            .orElse(LoggerFactory.getLogger(InMemoryPipelineRunner.class));

    itemsToProcess = new ArrayList<>();

    NotifyingItemFactory nif = new NotifyingItemFactory(itemFactory);
    nif.register(itemsToProcess::add);
    nif.register(i -> logger.debug("Item {} added to queue", i.getId()));

    this.itemFactory = nif;

  }

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory) {
    this(new SimplePipeline.Builder().from(pipelineDescriptor).build(), itemFactory);
  }

  @Override
  public void run() {

    while(running) {
      SourceResponse sr = pipeline.read(itemFactory);

      while(running && !itemsToProcess.isEmpty()) {
        Item item = itemsToProcess.remove(0);
        pipeline.process(item);
      }

      // If we are done, then we stop
      if(sr.getStatus() == SourceResponse.Status.DONE) {
        stop();
      }
    }
  }


  public void stop() {
    logger.info("Stopping pipeline after current item/source");
    running = false;
  }
}
