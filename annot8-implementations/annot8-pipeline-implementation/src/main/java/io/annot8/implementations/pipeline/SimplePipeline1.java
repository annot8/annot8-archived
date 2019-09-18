/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.pipeline;

import io.annot8.api.components.Processor;
import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.Source;
import io.annot8.api.components.SourceDescriptor;
import io.annot8.api.context.Context;
import io.annot8.api.exceptions.IncompleteException;
import io.annot8.api.pipelines.PipelineDescriptor;
import io.annot8.common.components.logging.Logging;
import io.annot8.common.components.metering.Metering;
import io.annot8.implementations.support.context.SimpleContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimplePipeline1 implements Pipeline {
  private final String name;
  private final String description;

  private final Collection<Source> sources;
  private final Collection<Processor> processors;
  private final Context context;

  private SimplePipeline1(
      String name,
      String description,
      Collection<Source> sources,
      Collection<Processor> processors,
      Context context) {
    this.name = name;
    this.description = description;
    this.sources = sources;
    this.processors = processors;
    this.context = context;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Context getContext() {
    return context;
  }

  public Collection<Source> getSources() {
    return sources;
  }

  public Collection<Processor> getProcessors() {
    return processors;
  }

  public static class Builder implements Pipeline.Builder {

    private final Context context;

    private String name;
    private String description;
    private List<Source> sources = new ArrayList<>();
    private List<Processor> processors = new ArrayList<>();

    public Builder(String name) {
      this.name = name;

      Logging logging = Logging.useLoggerFactory();
      Metering metering = Metering.useGlobalRegistry(name);
      this.context = new SimpleContext(logging, metering);
    }

    public Builder(PipelineDescriptor pipelineDescriptor) {
      this(pipelineDescriptor.getName());
      description = pipelineDescriptor.getDescription();

      from(pipelineDescriptor);
    }

    protected Builder from(PipelineDescriptor pipelineDescriptor) {
      pipelineDescriptor
          .getSources()
          .stream()
          .map(d -> d.create(context))
          .map(Source.class::cast)
          .forEach(this::withSource);

      pipelineDescriptor
          .getProcessors()
          .stream()
          .map(d -> d.create(context))
          .map(Processor.class::cast)
          .forEach(this::withProcessor);

      return this;
    }

    @Override
    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    @Override
    public Builder withSource(Source source) {
      sources.add(source);
      return this;
    }

    @Override
    public Builder withProcessor(Processor processor) {
      processors.add(processor);
      return this;
    }

    @Override
    public Pipeline build() throws IncompleteException {
      if (name == null || name.isEmpty()) {
        throw new IncompleteException("Pipeline must have a name");
      }

      if (sources.isEmpty()) {
        throw new IncompleteException("Pipeline requires at least one source");
      }

      if (processors.isEmpty()) {
        throw new IncompleteException("Pipeline requires at least one processor");
      }

      return new SimplePipeline1(name, description, sources, processors);
    }
  }
}
