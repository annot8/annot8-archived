/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.registries;

import io.annot8.core.capabilities.AbstractComponentCapabilities;
import io.annot8.core.capabilities.ComponentCapabilities;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

public class TestProcessor implements Processor {

  @Override
  public ProcessorResponse process(Item item) {
    return ProcessorResponse.ok();
  }

  @Override
  public ComponentCapabilities getCapabilities() {
    return new AbstractComponentCapabilities() {};
  }
}
