/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.base;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.elements.PipeBuilder;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

public abstract class AbstractPipeBuilder implements PipeBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPipeBuilder.class);

  private final ComponentHolder<Processor> processorHolder = new ComponentHolder<>();
  private final ResourcesHolder resourcesHolder = new ResourcesHolder();
  private String name = "anonymous";

  @Override
  public PipeBuilder addResource(
      final String id, final Resource resource) {
    resourcesHolder.addResource(id, resource);
    return this;
  }

  @Override
  public PipeBuilder addProcessor(
      final Processor processor) {
    processorHolder.add(processor);
    return this;
  }

  @Override
  public PipeBuilder withName(String name) {
    this.name = name;
    return this;
  }

  protected ComponentHolder<Processor> getProcessorHolder() {
    return processorHolder;
  }

  protected ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  protected String getName() {
    return name;
  }
}
