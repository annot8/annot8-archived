/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines;

import java.util.Collection;

import io.annot8.core.components.Processor;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.helpers.WithDescription;
import io.annot8.core.helpers.WithName;

public interface Pipeline extends WithName, WithDescription {
  Collection<Source> getSources();

  Collection<Processor> getProcessors();

  interface Builder {
    Pipeline.Builder from(Pipeline pipeline);

    Pipeline.Builder withName(String name);

    Pipeline.Builder withDescription(String description);

    Pipeline.Builder withSource(Source source);

    Pipeline.Builder withProcessor(Processor processor);

    default Pipeline.Builder withSources(Source... sources) {
      for (Source source : sources) withSource(source);

      return this;
    }

    default Pipeline.Builder withSources(Collection<Source> sources) {
      for (Source source : sources) withSource(source);

      return this;
    }

    default Pipeline.Builder withProcessors(Processor... processors) {
      for (Processor processor : processors) withProcessor(processor);

      return this;
    }

    default Pipeline.Builder withProcessors(Collection<Processor> processors) {
      for (Processor processor : processors) withProcessor(processor);

      return this;
    }

    Pipeline build() throws IncompleteException;
  }
}
