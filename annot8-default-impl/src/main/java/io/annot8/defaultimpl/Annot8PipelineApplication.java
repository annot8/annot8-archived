/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.implementations.registries.ContentBuilderFactoryRegistry;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.elements.PipelineBuilder;
import io.annot8.common.pipelines.queues.MemoryItemQueue;
import io.annot8.common.pipelines.simple.SimplePipelineBuilder;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.defaultimpl.factories.DefaultBaseItemFactory;
import io.annot8.defaultimpl.factories.DefaultContentBuilderFactoryRegistry;

public class Annot8PipelineApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(Annot8PipelineApplication.class);

  private final Consumer<PipelineBuilder> pipelineBuilderConsumer;

  private final Consumer<ContentBuilderFactoryRegistry> contentBuilderFactoryRegistryConsumer;

  public Annot8PipelineApplication(Consumer<PipelineBuilder> pipelineBuilderConsumer) {
    this(pipelineBuilderConsumer, c -> {});
  }

  public Annot8PipelineApplication(
      Consumer<PipelineBuilder> pipelineBuilderConsumer,
      Consumer<ContentBuilderFactoryRegistry> contentBuilderFactoryRegistryConsumer) {
    this.pipelineBuilderConsumer = pipelineBuilderConsumer;
    this.contentBuilderFactoryRegistryConsumer = contentBuilderFactoryRegistryConsumer;
  }

  public void run() {
    try {
      Pipeline pipeline = buildPipeline();
      pipeline.configure(new SimpleContext());
      runPipeline(pipeline);
    } catch (Exception e) {
      LOGGER.error("Unable to run pipeline", e);
    }
  }

  private void runPipeline(Pipeline pipeline) throws Exception {
    try (pipeline) {
      pipeline.run();
    }
  }

  private Pipeline buildPipeline() throws IncompleteException {
    PipelineBuilder builder = new SimplePipelineBuilder();
    builder = configureBuilder(builder);

    if (pipelineBuilderConsumer != null) {
      pipelineBuilderConsumer.accept(builder);
    }

    return builder.build();
  }

  private PipelineBuilder configureBuilder(PipelineBuilder builder) {

    DefaultContentBuilderFactoryRegistry contentBuilderFactoryRegistry =
        new DefaultContentBuilderFactoryRegistry();
    contentBuilderFactoryRegistryConsumer.accept(contentBuilderFactoryRegistry);
    DefaultBaseItemFactory itemFactory = new DefaultBaseItemFactory(contentBuilderFactoryRegistry);
    MemoryItemQueue itemQueue = new MemoryItemQueue();
    return builder.withQueue(itemQueue).withItemFactory(itemFactory);
  }

  public static void main() {
    // Example of use

    new Annot8PipelineApplication(
            builder -> {

              // builder.addSource(FileSource.class);
              // builder.addProcessor(EmailProcessor.class);

            })
        .run();
  }
}
