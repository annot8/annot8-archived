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

  PipeBuilder addResource(final String id, final Resource resource);

  PipeBuilder addProcessor(final Processor processor);


  Pipe build() throws IncompleteException;
}
