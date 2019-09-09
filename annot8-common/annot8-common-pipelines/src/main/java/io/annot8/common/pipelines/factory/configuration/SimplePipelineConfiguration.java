/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.ArrayList;
import java.util.List;

public class SimplePipelineConfiguration implements PipelineConfiguration {

  private List<ComponentConfiguration> processors = new ArrayList<>();
  private List<ComponentConfiguration> sources = new ArrayList<>();
  private List<ComponentConfiguration> resources = new ArrayList<>();

  public SimplePipelineConfiguration() {}

  public SimplePipelineConfiguration(
      List<ComponentConfiguration> sources,
      List<ComponentConfiguration> processors,
      List<ComponentConfiguration> resources) {
    this.processors.addAll(processors);
    this.sources.addAll(sources);
  }

  public List<ComponentConfiguration> getProcessors() {
    return processors;
  }

  public void setProcessors(List<ComponentConfiguration> processors) {
    this.processors = processors;
  }

  public List<ComponentConfiguration> getSources() {
    return sources;
  }

  public void setSources(List<ComponentConfiguration> sources) {
    this.sources = sources;
  }

  public List<ComponentConfiguration> getResources() {
    return resources;
  }

  public void setResources(List<ComponentConfiguration> resources) {
    this.resources = resources;
  }
}
