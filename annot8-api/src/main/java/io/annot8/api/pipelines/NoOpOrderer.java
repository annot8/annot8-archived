/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.pipelines;

import io.annot8.api.components.ProcessorDescriptor;
import io.annot8.api.components.SourceDescriptor;
import java.util.Collection;

public final class NoOpOrderer implements PipelineOrderer {

  private static final NoOpOrderer INSTANCE = new NoOpOrderer();

  private NoOpOrderer() {
    // Private constructor for singleton
  }

  public static final NoOpOrderer getInstance() {
    return INSTANCE;
  }

  @Override
  public Collection<ProcessorDescriptor> orderProcessors(
      Collection<ProcessorDescriptor> processors) {
    return processors;
  }

  @Override
  public Collection<SourceDescriptor> orderSources(Collection<SourceDescriptor> sources) {
    return sources;
  }
}
