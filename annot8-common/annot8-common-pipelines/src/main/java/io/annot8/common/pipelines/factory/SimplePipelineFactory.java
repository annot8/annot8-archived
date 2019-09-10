/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.registries.Annot8ComponentRegistry;
import io.annot8.common.pipelines.elements.PipeBuilder;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.elements.PipelineBuilder;
import io.annot8.common.pipelines.factory.configuration.ComponentConfiguration;
import io.annot8.common.pipelines.factory.configuration.PipelineConfiguration;
import io.annot8.common.pipelines.factory.configuration.TypedComponentConfiguration;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public class SimplePipelineFactory implements PipelineFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipelineFactory.class);

  private final Supplier<PipelineBuilder> pipelineBuilderSupplier;
  private final Supplier<PipeBuilder> pipeBuilderSupplier;
  private final Annot8ComponentRegistry componentRegistry;

  public SimplePipelineFactory(
      Supplier<PipelineBuilder> pipelineBuilderSupplier,
      Supplier<PipeBuilder> pipeBuilderSupplier,
      Annot8ComponentRegistry componentRegistry) {
    this.pipelineBuilderSupplier = pipelineBuilderSupplier;
    this.pipeBuilderSupplier = pipeBuilderSupplier;
    this.componentRegistry = componentRegistry;
  }

  @Override
  public Pipeline create(PipelineConfiguration pipelineConfiguration) throws IncompleteException {
    return createPipeline(pipelineConfiguration);
  }

  private Pipeline createPipeline(PipelineConfiguration configuration) throws IncompleteException {
    PipelineBuilder pipelineBuilder = pipelineBuilderSupplier.get();

    configuration
        .getSources()
        .forEach(
            s -> addComponentToBuilder(Source.class, s, pipelineBuilder::addSource));

    // Currently just convert to a pipe and pass that in
    PipeBuilder pipeBuilder = pipeBuilderSupplier.get();
    configuration
        .getProcessors()
        .forEach(
            s ->
                addComponentToBuilder(
                    Processor.class, s, pipeBuilder::addProcessor));

    pipelineBuilder.addPipe(pipeBuilder);

    configuration
        .getResources()
        .forEach(
            s ->
                addComponentToBuilder(
                    Resource.class, s, (i) -> pipelineBuilder.addResource(s.getName(), i)));

    return pipelineBuilder.build();
  }

  private <T extends Annot8Component> void addComponentToBuilder(
      Class<T> clazz, ComponentConfiguration config, Consumer<T> consumer) {

    try {
      TypedComponentConfiguration<T> tcc = validateComponent(config, clazz);

      T s = createInstance(tcc.getComponentClass(), config.getSettings());
      consumer.accept(s);

    } catch (Annot8Exception e) {
      LOGGER.warn(e.getMessage());
      LOGGER.debug("Exception is:", e);
    }
  }

  private <T extends Annot8Component> TypedComponentConfiguration<T> validateComponent(
      ComponentConfiguration config, Class<T> componentType) throws Annot8Exception {
    try {
      Optional<Class<? extends T>> optional =
          componentRegistry.getComponent(config.getComponent(), componentType);
      Class<? extends T> componentClass = optional.get();
      return new TypedComponentConfiguration<>(componentClass, config.getSettings());
    } catch (Exception e) {
      throw new Annot8Exception(
          "Could not find class implementation for component name " + config.getComponent(), e);
    }
  }

  private <T> T createInstance(Class<T> clazz, Settings settings) throws Annot8Exception {
    try {

      // if settings == null use the for the default constructor
      if(settings == null) {
        return clazz.getConstructor().newInstance();
      }

      // Otherwise look for a constructor which takes the settings class
      return clazz.getConstructor(settings.getClass()).newInstance(settings);

    } catch (Exception e) {
      throw new Annot8Exception("Could not create instance of component " + clazz.getName(), e);
    }
  }
}
