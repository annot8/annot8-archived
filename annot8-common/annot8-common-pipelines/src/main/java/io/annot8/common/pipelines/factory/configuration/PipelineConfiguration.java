/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.Collection;

public interface PipelineConfiguration {

  Collection<ComponentConfiguration> getProcessors();

  Collection<ComponentConfiguration> getSources();

  Collection<ComponentConfiguration> getResources();
}
