/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.pipeline;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.annot8.common.pipelines.factory.configuration.ComponentConfiguration;
import io.annot8.core.settings.Settings;

public class TestPipelineComponentConfiguration implements ComponentConfiguration {
  
  private final String component;

  private final Set<Settings> settings;

  public TestPipelineComponentConfiguration(String component, Set<Settings> settings) {
    this.component = component;
    this.settings = settings;
  }

  public TestPipelineComponentConfiguration(String component, Settings... settings) {
    this(component, new HashSet<>(Arrays.asList(settings)));
  }

  @Override
  public String getName() {
    return "test";
  }

  @Override
  public String getComponent() {
    return component;
  }

  @Override
  public Set<Settings> getSettings() {
    return settings;
  }
}
