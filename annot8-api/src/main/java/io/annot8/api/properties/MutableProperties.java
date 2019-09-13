/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.properties;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/** Base mutable properties interface from which all other mutable properties extend. */
public interface MutableProperties extends Properties {

  /**
   * Set the property value for the specified key
   *
   * @param key the key
   * @param value the value
   */
  void set(final String key, final Object value);

  /**
   * Remove the property for the specified key, and return it's object (if it exists)
   *
   * @param key the key to remove
   * @return object which was present at that key (if it existed)
   */
  Optional<Object> remove(final String key);

  /**
   * Set the current properties to be equal to the map
   *
   * @param properties map to set
   */
  default void set(final Map<String, Object> properties) {
    removeAll();
    add(properties);
  }

  /** Return a map of all properties */
  default void removeAll() {
    keys().forEach(this::remove);
  }

  /**
   * Add all properties from the given map, overwriting values where they already exist
   *
   * @param properties the map to add
   */
  default void add(final Map<String, Object> properties) {
    if (properties != null) {
      properties.forEach(this::set);
    }
  }

  /**
   * Remove all properties that match the given keys
   *
   * @param keys the keys to remove
   */
  default void remove(final Collection<String> keys) {
    if (keys != null) {
      keys.forEach(this::remove);
    }
  }
}
