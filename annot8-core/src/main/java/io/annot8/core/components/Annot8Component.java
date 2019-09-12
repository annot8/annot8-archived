/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import java.util.Collections;
import java.util.Map;

import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;
import io.annot8.core.settings.Settings;

/** Base interface from which all other Annot8 components extend. */
public interface Annot8Component<S extends Settings> extends AutoCloseable {

  /**
   * Configure this component using information from the given context.
   *
   * <p>This may be called at multiple times, and the component should re-configure as required.
   */
  default void configure(final S settings, final Map<String, Resource> resources)
      throws BadConfigurationException, MissingResourceException {
    // Do nothing
  }

  default void configure(final S settings)
      throws BadConfigurationException, MissingResourceException {
    configure(settings, Collections.emptyMap());
  }

  @Override
  default void close() {
    // Do nothing
  }
}
