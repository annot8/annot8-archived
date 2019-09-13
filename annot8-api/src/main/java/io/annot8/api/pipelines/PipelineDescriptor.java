/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.pipelines;

import java.util.Collection;

import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.SourceDescriptor;
import io.annot8.api.exceptions.IncompleteException;
import io.annot8.api.helpers.WithDescription;
import io.annot8.api.helpers.WithName;

/**
 * Describes a pipeline, which is a collection of ordered sources and processors.
 *
 * The ordering is implementation dependent (i.e. an implementation may return processors in the order they were added,
 * or in the order that makes sense based on their capabilities)
 */
public interface PipelineDescriptor extends WithName, WithDescription {
  /**
   * Return an ordered list of {@link SourceDescriptor}s describing the sources that will feed data into the pipeline
   */
  Collection<SourceDescriptor> getSources();

  /**
   * Return an ordered list of {@link ProcessorDescriptor}s describing the processes that will process data in the pipeline
   */
  Collection<ProcessorDescriptor> getProcessors();

  /**
   * Builder interface for building new pipeline descriptors
   */
  interface Builder {
    /**
     * Add sources and processors from an existing pipeline descriptor to the one being built
     */
    PipelineDescriptor.Builder from(PipelineDescriptor pipelineDescriptor);

    /**
     * Set the name of the pipeline
     */
    PipelineDescriptor.Builder withName(String name);

    /**
     * Set a description of the pipeline
     */
    PipelineDescriptor.Builder withDescription(String description);

    /**
     * Add a source to the pipeline that is being built
     */
    PipelineDescriptor.Builder withSource(SourceDescriptor source);

    /**
     * Add a processor to the pipeline that is being built
     */
    PipelineDescriptor.Builder withProcessor(ProcessorDescriptor processor);

    /**
     * Add multiple sources to the pipeline that is being built, in the order provided
     */
    default PipelineDescriptor.Builder withSources(SourceDescriptor... sources) {
      for (SourceDescriptor source : sources) withSource(source);

      return this;
    }

    /**
     * Add multiple sources to the pipeline that is being built, in the order provided
     */
    default PipelineDescriptor.Builder withSources(Collection<SourceDescriptor> sources) {
      for (SourceDescriptor source : sources) withSource(source);

      return this;
    }

    /**
     * Add multiple processors to the pipeline that is being built, in the order provided
     */
    default PipelineDescriptor.Builder withProcessors(ProcessorDescriptor... processors) {
      for (ProcessorDescriptor processor : processors) withProcessor(processor);

      return this;
    }

    /**
     * Add multiple processors to the pipeline that is being built, in the order provided
     */
    default PipelineDescriptor.Builder withProcessors(Collection<ProcessorDescriptor> processors) {
      for (ProcessorDescriptor processor : processors) withProcessor(processor);

      return this;
    }

    /**
     * Build the pipeline descriptor described by this builder
     *
     * @throws IncompleteException
     *    If the pipeline is missing components (i.e. doesn't have at least 1 source and processor)
     */
    PipelineDescriptor build() throws IncompleteException;
  }
}
