/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.configuration.ComponentConfigurer;
import io.annot8.common.pipelines.base.AbstractPipelineBuilder;
import io.annot8.common.pipelines.base.AbstractTask;
import io.annot8.common.pipelines.definitions.PipelineDefinition;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.feeders.MultiItemFeeder;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.common.pipelines.plumbing.PipelinePlumber;
import io.annot8.common.pipelines.queues.ProcessQueueSourceListener;
import io.annot8.common.pipelines.queues.QueuingSupport;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.context.Context;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class SimplePipeline extends AbstractTask implements Pipeline {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipeline.class);

  // This the the pipeline definition
  private final PipelineDefinition definition;

  // These are from the pipeline instance
  private Map<String, Resource> resources;
  private List<Source> sources;
  private Pipe pipe;
  private QueueFeeder queueFeeder;
  private ItemFactory itemFactory;
  private PipelinePlumber plumber;

  public SimplePipeline(PipelineDefinition definition) {
    super(definition.getName());
    this.definition = definition;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {

    // Close old values
    close();

    // COnverter
    // Hook up the item queuing
    QueuingSupport support =
        new QueuingSupport(definition.getQueue(), definition.getBaseItemFactory());
    queueFeeder = support.getQueueFeeder();
    itemFactory = support.getItemFactory();

    // Create a new
    Objects.requireNonNull(itemFactory);

    ComponentConfigurer componentConfigurer = new ComponentConfigurer(context);
    resources = componentConfigurer.configureResources(definition.getResourcesHolder());
    sources = componentConfigurer.configureComponents(definition.getSourceHolder());

    // Configure all our pipes (under a single pipe)

    plumber =
        new PipelinePlumber(
            definition.getPipes(), definition.getBranches(), definition.getMerges());

    plumber.plumb(AbstractPipelineBuilder.DEFAULT_PIPE);
    componentConfigurer.configureComponent(plumber);
    pipe = plumber.getPipe();
  }

  @Override
  protected void perform() {
    MultiItemFeeder feeder = new MultiItemFeeder(itemFactory, sources);
    feeder.register(new ProcessQueueSourceListener(queueFeeder, pipe));
    feeder.feed(pipe);
  }

  @Override
  public void close() {
    if (plumber != null) {
      plumber.close();
    }

    if (pipe != null) {
      pipe.close();
    }

    if (sources != null) {
      sources.forEach(Annot8Component::close);
      sources.clear();
    }

    if (resources != null) {
      resources.values().forEach(Annot8Component::close);
      resources.clear();
    }

    itemFactory = null;
    queueFeeder = null;
    pipe = null;
    plumber = null;
  }
}
