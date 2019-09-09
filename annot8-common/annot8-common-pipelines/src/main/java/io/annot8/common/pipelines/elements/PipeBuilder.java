/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import java.util.Arrays;
import java.util.Collection;

import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface PipeBuilder {

  PipeBuilder withName(String name);

  default PipeBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  default PipeBuilder addProcessor(final Processor processor, final Settings... settings) {
    return addProcessor(processor, Arrays.asList(settings));
  }

  PipeBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  PipeBuilder addProcessor(final Processor processor, final Collection<Settings> settings);

  Pipe build() throws IncompleteException;
}
