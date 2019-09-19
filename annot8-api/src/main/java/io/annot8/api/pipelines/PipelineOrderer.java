package io.annot8.api.pipelines;

import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.SourceDescriptor;

import java.util.Collection;

public interface PipelineOrderer {
  Collection<ProcessorDescriptor> orderProcessors(Collection<ProcessorDescriptor> processors);
  Collection<SourceDescriptor> orderSources(Collection<SourceDescriptor> sources);
}
