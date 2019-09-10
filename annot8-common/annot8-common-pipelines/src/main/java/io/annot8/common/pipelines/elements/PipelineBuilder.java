/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import java.util.Arrays;
import java.util.Collection;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.queues.BaseItemQueue;
import io.annot8.common.pipelines.simple.ProcessorPipe;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface PipelineBuilder {

  String DEFAULT_PIPE = "DEFAULT";

  PipelineBuilder withName(String name);

  default PipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  default PipelineBuilder addSource(final Source source, final Settings... settings) {
    addSource(source, Arrays.asList(settings));
    return this;
  }

  PipelineBuilder addSource(final Source source, final Collection<Settings> settings);

  default PipelineBuilder addPipe(final PipeBuilder pipe) throws IncompleteException {
    addPipe(DEFAULT_PIPE, pipe);
    return this;
  }

  default PipelineBuilder addPipe(String key, final PipeBuilder pipe) throws IncompleteException {
    addPipe(key, pipe.build());
    return this;
  }

  default PipelineBuilder addPipe(Pipe pipe) {
    addPipe(DEFAULT_PIPE, pipe);
    return this;
  }

  PipelineBuilder addPipe(String key, Pipe pipe);

  default PipelineBuilder addProcessor(Processor processor) {
    addProcessor(DEFAULT_PIPE, processor);
    return this;
  }

  default PipelineBuilder addProcessor(String key, Processor processor) {
    addPipe(DEFAULT_PIPE, new ProcessorPipe(processor));
    return this;
  }

  PipelineBuilder addBranch(final BranchDefinition branchDefinition);

  PipelineBuilder addMerge(final MergeDefinition definition);

  PipelineBuilder withQueue(BaseItemQueue queue);

  PipelineBuilder withItemFactory(BaseItemFactory itemFactory);

  Pipeline build() throws IncompleteException;
}
