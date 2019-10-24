/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.helpers.builders;

import io.annot8.api.properties.Properties;
import java.util.Optional;

/**
 * Helper interface to indicate that a builder should processor adding properties to an object
 *
 * @param <A> The builder class
 */
public interface WithPropertiesBuilder<A> {

  /**
   * Add a property
   *
   * @param key the key
   * @param value the value
   * @return a builder with the key-value property pair added to it
   */
  A withProperty(final String key, final Object value);

  /**
   * Add a property, if it is present (don't add it otherwise)
   *
   * @param key the key
   * @param value the value
   * @return a builder with the key-value property pair added to it, if the value is present
   */
  A withPropertyIfPresent(final String key, final Optional<?> value);

  /**
   * Remove a property with matching key and value
   *
   * @param key the key to remove
   * @param value the value to remove
   * @return a builder with the key-value property pair removed from it
   */
  A withoutProperty(final String key, final Object value);

  /**
   * Remove a property
   *
   * @param key the key to remove
   * @return a builder with the key removed from it
   */
  A withoutProperty(final String key);

  /**
   * Add in existing properties
   *
   * @param properties to add
   * @return a builder with the specified properties
   */
  A withProperties(final Properties properties);
}
