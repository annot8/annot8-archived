/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.context.Context;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

/** Base interface from which all other Annot8 components extend. */
public interface Annot8Component extends AutoCloseable {

  /**
   * Configure this component using information from the given context.
   *
   * <p>This may be called at multiple times, and the component should re-configure as required.
   *
   * @param context context in which the component will run
   */
  default void configure(final Context context)
      throws BadConfigurationException, MissingResourceException {
    // Do nothing
  }

  @Override
  default void close() {
    // Do nothing
  }

  /**
   * Add the capabilities of this component.
   *
   * <p>There is no need to implement this IF you are using annotation based capabilities.
   *
   * <p>If you do override this ensure you call super.buildCapabilities(builder).
   *
   * @param builder the builder to add capabilities to
   */
  default void buildCapabilities(Capabilities.Builder builder) {
    // Assumes annotation based, so no further implementation is required
  }
}
