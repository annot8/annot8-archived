/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import io.annot8.core.capabilities.AbstractComponentCapabilities;
import io.annot8.core.capabilities.ComponentCapabilities;
import io.annot8.core.components.Annot8Component;

public class TestComponent implements Annot8Component {
  @Override
  public ComponentCapabilities getCapabilities() {
    return new AbstractComponentCapabilities() {};
  }
}
