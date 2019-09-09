/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.pipelines.base.AbstractPipelineBuilder;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.core.exceptions.IncompleteException;

public class SimplePipelineBuilder extends AbstractPipelineBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipelineBuilder.class);

  @Override
  public Pipeline build() throws IncompleteException {
    return new SimplePipeline(getDefinition());
  }
}
