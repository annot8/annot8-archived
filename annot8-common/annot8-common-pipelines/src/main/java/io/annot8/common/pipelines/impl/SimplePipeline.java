/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.annot8.common.pipelines.Pipeline;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;

public class SimplePipeline implements Pipeline {
  private final String name;
  private final String description;

  private final Collection<Source> sources;
  private final Collection<Processor> processors;

  private SimplePipeline(
      String name,
      String description,
      Collection<Source> sources,
      Collection<Processor> processors) {
    this.name = name;
    this.description = description;
    this.sources = sources;
    this.processors = processors;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Collection<Source> getSources() {
    return sources;
  }

  @Override
  public Collection<Processor> getProcessors() {
    return processors;
  }

  static class Builder implements Pipeline.Builder {

    private String name;
    private String description;
    private List<Source> sources = new ArrayList<>();
    private List<Processor> processors = new ArrayList<>();

    @Override
    public Builder from(Pipeline pipeline) {
      name = pipeline.getName();
      description = pipeline.getDescription();
      sources.addAll(pipeline.getSources());
      processors.addAll(pipeline.getProcessors());

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
    public SimplePipeline build() throws IncompleteException {
      if (name == null || name.isEmpty()) {
        throw new IncompleteException("Pipeline must have a name");
      }

      if (sources.isEmpty()) {
        throw new IncompleteException("Pipeline requires at least one source");
      }

      if (processors.isEmpty()) {
        throw new IncompleteException("Pipeline requires at least one processor");
      }

      return new SimplePipeline(name, description, sources, processors);
    }
  }
}
