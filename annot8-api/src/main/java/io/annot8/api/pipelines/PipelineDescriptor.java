/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.pipelines;

import java.util.Collection;

import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.SourceDescriptor;
import io.annot8.api.exceptions.IncompleteException;
import io.annot8.api.helpers.WithDescription;
import io.annot8.api.helpers.WithName;

public interface PipelineDescriptor extends WithName, WithDescription {
  Collection<SourceDescriptor> getSources();

  Collection<ProcessorDescriptor> getProcessors();

  interface Builder {
    PipelineDescriptor.Builder from(PipelineDescriptor pipelineDescriptor);

    PipelineDescriptor.Builder withName(String name);

    PipelineDescriptor.Builder withDescription(String description);

    PipelineDescriptor.Builder withSource(SourceDescriptor source);

    PipelineDescriptor.Builder withProcessor(ProcessorDescriptor processor);

    default PipelineDescriptor.Builder withSources(SourceDescriptor... sources) {
      for (SourceDescriptor source : sources) withSource(source);

      return this;
    }

    default PipelineDescriptor.Builder withSources(Collection<SourceDescriptor> sources) {
      for (SourceDescriptor source : sources) withSource(source);

      return this;
    }

    default PipelineDescriptor.Builder withProcessors(ProcessorDescriptor... processors) {
      for (ProcessorDescriptor processor : processors) withProcessor(processor);

      return this;
    }

    default PipelineDescriptor.Builder withProcessors(Collection<ProcessorDescriptor> processors) {
      for (ProcessorDescriptor processor : processors) withProcessor(processor);

      return this;
    }

    PipelineDescriptor build() throws IncompleteException;
  }
}
