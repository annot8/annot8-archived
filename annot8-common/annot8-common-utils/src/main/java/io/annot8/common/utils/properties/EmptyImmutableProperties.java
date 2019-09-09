/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.utils.properties;

import java.util.Collections;
import java.util.Map;

import io.annot8.core.properties.ImmutableProperties;

/** Empty implementation of ImmutableProperties interface */
public final class EmptyImmutableProperties implements ImmutableProperties {

  private static final EmptyImmutableProperties INSTANCE = new EmptyImmutableProperties();

  private EmptyImmutableProperties() {
    // Empty constructor
  }

  /**
   * Get the singleton instance of EmptyImmutableProperties
   *
   * @return instance
   */
  public static EmptyImmutableProperties getInstance() {
    return INSTANCE;
  }

  @Override
  public Map<String, Object> getAll() {
    return Collections.emptyMap();
  }

  @Override
  public String toString() {
    return "EmptyImmutableProperties";
  }
}
