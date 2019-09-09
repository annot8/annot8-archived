/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.pipelines.base.AbstractPipeBuilder;
import io.annot8.common.pipelines.elements.Pipe;

public class SimplePipeBuilder extends AbstractPipeBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipeBuilder.class);

  @Override
  public Pipe build() {
    return new SimplePipe(getName(), getResourcesHolder(), getProcessorHolder());
  }
}
