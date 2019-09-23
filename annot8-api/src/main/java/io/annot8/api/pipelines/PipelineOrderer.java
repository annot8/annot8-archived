/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.pipelines;

import java.util.Collection;

import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.SourceDescriptor;

public interface PipelineOrderer {
  Collection<ProcessorDescriptor> orderProcessors(Collection<ProcessorDescriptor> processors);

  Collection<SourceDescriptor> orderSources(Collection<SourceDescriptor> sources);
}
