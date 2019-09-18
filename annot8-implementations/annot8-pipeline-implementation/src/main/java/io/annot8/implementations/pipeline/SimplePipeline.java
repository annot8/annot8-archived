package io.annot8.implementations.pipeline;

import io.annot8.api.components.Annot8Component;
import io.annot8.api.components.Annot8ComponentDescriptor;
import io.annot8.api.components.Processor;
import io.annot8.api.components.Source;
import io.annot8.api.context.Context;
import io.annot8.api.data.Item;
import io.annot8.api.data.ItemFactory;
import io.annot8.api.pipelines.PipelineDescriptor;
import io.annot8.common.components.logging.Logging;
import io.annot8.common.components.metering.Metering;
import io.annot8.implementations.support.context.SimpleContext;
import io.annot8.implementations.support.stores.NotifyingItemFactory;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class SimplePipeline {

  private final PipelineDescriptor pipelineDescriptor;
  private final ItemFactory itemFactory;
  private final Context context;
  private final Logger logger;


  public SimplePipeline(PipelineDescriptor pipelineDescriptor) {
    this.pipelineDescriptor = pipelineDescriptor;
    List<Item> itemsToProcess = new ArrayList<>();


    this.logger = logging.getLogger(InMemoryPipelineRunner.class);

    NotifyingItemFactory nif = new NotifyingItemFactory(itemFactory);
    nif.register(itemsToProcess::add);
    nif.register(i -> logger.debug("Item {} added to queue", i.getId()));


  }

  private <T extends Annot8Component> T create(
      Annot8ComponentDescriptor<T, ?> descriptor, Class<T> clazz) {
    return descriptor.create(context);
  }
}
