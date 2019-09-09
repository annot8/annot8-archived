/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.pipeline;

import java.util.Collection;
import java.util.Collections;

import io.annot8.common.pipelines.factory.configuration.ComponentConfiguration;
import io.annot8.common.pipelines.factory.configuration.PipelineConfiguration;

public class TestPipelineConfiguration implements PipelineConfiguration {

  private final Collection<ComponentConfiguration> processors;

  private final Collection<ComponentConfiguration> sources;

  private final Collection<ComponentConfiguration> resources;

  public TestPipelineConfiguration() {
    this(null, null, null);
  }

  public TestPipelineConfiguration(
      Collection<ComponentConfiguration> sources,
      Collection<ComponentConfiguration> processors,
      Collection<ComponentConfiguration> resources) {
    this.processors = processors == null ? Collections.emptyList() : processors;
    this.sources = sources == null ? Collections.emptyList() : sources;
    this.resources = resources == null ? Collections.emptyList() : resources;
  }

  @Override
  public Collection<ComponentConfiguration> getProcessors() {
    return processors;
  }

  @Override
  public Collection<ComponentConfiguration> getSources() {
    return sources;
  }

  @Override
  public Collection<ComponentConfiguration> getResources() {
    return resources;
  }
}
