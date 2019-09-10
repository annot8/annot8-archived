/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.context.Context;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

@ExtendWith(MockitoExtension.class)
class Annot8ComponentTest {

  @Mock Capabilities.Builder builder;

  @Mock Context context;

  @Test
  void defaultImplementationsDontThrow()
      throws BadConfigurationException, MissingResourceException {
    Annot8Component component = new Annot8Component() {
          // Sub class
        };

    component.buildCapabilities(builder);
    component.configure(context);
    component.close();
  }
}
