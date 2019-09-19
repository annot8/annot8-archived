/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.context.Context;
import io.annot8.api.data.ItemFactory;
import io.annot8.api.pipelines.Pipeline;
import io.annot8.api.pipelines.PipelineDescriptor;
import io.annot8.api.pipelines.PipelineRunner;
import io.annot8.common.components.logging.Logging;
import io.annot8.implementations.support.stores.QueueItemFactory;

public class InMemoryPipelineRunner implements PipelineRunner {

  private final Pipeline pipeline;
  private final Logger logger;
  private final QueueItemFactory itemFactory;

  private boolean running = true;

  public InMemoryPipelineRunner(Pipeline pipeline, ItemFactory itemFactory) {
    this.pipeline = pipeline;
    this.logger =
        pipeline
            .getContext()
            .getResource(Logging.class)
            .map(l -> l.getLogger(InMemoryPipelineRunner.class))
            .orElse(LoggerFactory.getLogger(InMemoryPipelineRunner.class));

    this.itemFactory = new QueueItemFactory(itemFactory);
    this.itemFactory.register(i -> logger.debug("Item {} added to queue", i.getId()));
  }

  public InMemoryPipelineRunner(PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory) {
    this(new SimplePipeline.Builder().from(pipelineDescriptor).build(), itemFactory);
  }

  public InMemoryPipelineRunner(
      PipelineDescriptor pipelineDescriptor, ItemFactory itemFactory, Context context) {
    this(
        new SimplePipeline.Builder().from(pipelineDescriptor).withContext(context).build(),
        itemFactory);
  }

  @Override
  public void run() {
    logger.info("Pipeline {} started", pipeline.getName());

    while (running) {
      SourceResponse sr = pipeline.read(itemFactory);

      while (running && !itemFactory.isEmpty()) {
        itemFactory.next().ifPresent(pipeline::process);
      }

      // If we are done, then we stop
      if (sr.getStatus() == SourceResponse.Status.DONE) {
        stop();
      }
    }
  }

  public void stop() {
    logger.info("Stopping pipeline after current item/source");
    running = false;
  }
}
