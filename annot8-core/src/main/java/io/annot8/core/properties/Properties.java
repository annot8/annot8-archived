/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.properties;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Base properties interface from which all other properties objects/interfaces extend. */
public interface Properties {

  static boolean equals(Properties a, Properties b) {
    return a.getAll().equals(b.getAll());
  }

  static int hashCode(Properties properties) {
    return properties.getAll().hashCode();
  }

  /**
   * Check if a key exists
   *
   * @param key the key
   * @return true if a property with the given key exists
   */
  default boolean has(final String key) {
    return getAll().containsKey(key);
  }

  /**
   * Check if key existing and its value is of a specific class
   *
   * @return true if a property of the given class with the given key exists
   */
  default boolean has(final String key, final Class<?> clazz) {
    return getAll(clazz).containsKey(key);
  }

  /**
   * Get the proprety value
   *
   * @param key the key
   * @return the property value for the specified key, if it exists
   */
  default Optional<Object> get(final String key) {
    return Optional.ofNullable(getAll().get(key));
  }

  /**
   * Get the property value of specified class
   *
   * @param key the key
   * @param clazz the requried value classs
   * @return the property value as the specified class for the specified key, if it exists
   */
  default <T> Optional<T> getProperty(final String key, final Class<T> clazz) {
    return Optional.ofNullable(getAll(clazz).get(key));
  }

  /**
   * Get the value or use a default if missing
   *
   * @param key the key
   * @param defaultValue the value to return if key is not present
   * @return the property value as an Object for the specified key, or a default value if the key
   *     doesn't exist
   */
  default Object getObjectOrDefault(final String key, final Object defaultValue) {
    return get(key).orElse(defaultValue);
  }

  /**
   * Get a specific class at the key, or use the default
   *
   * @param key the key
   * @param defaultValue the value to return if key is missing or of different type (non-null)
   * @return the property value for the specified key, or a default value if the key doesn't exist
   */
  @SuppressWarnings("unchecked")
  default <T> T getOrDefault(final String key, final T defaultValue) {
    final Class<?> clazz = defaultValue.getClass();
    final Object o = getObjectOrDefault(key, defaultValue.getClass());
    if (clazz.isInstance(o)) {
      return (T) clazz.cast(o);
    } else {
      return defaultValue;
    }
  }

  /**
   * Get all keys
   *
   * @return all currently set property keys
   */
  default Stream<String> keys() {
    return getAll().keySet().stream();
  }

  /**
   * Get all keys which have the value an instance of class
   *
   * @param clazz the class to filter on
   * @return all the property keys that have a value of the specified class
   */
  default <T> Stream<String> listKeys(final Class<T> clazz) {
    return getAll(clazz).keySet().stream();
  }

  /**
   * Get the properties as a map
   *
   * @return a map of all properties
   */
  Map<String, Object> getAll();

  /**
   * Get all properties which have a value instance of class
   *
   * @param clazz the class to filter on
   * @return a map of all properties that have a value of the specified class
   */
  default <T> Map<String, T> getAll(final Class<T> clazz) {
    return getAll()
        .entrySet()
        .stream()
        .filter(e -> clazz.isInstance(e.getValue()))
        .collect(Collectors.toMap(Entry::getKey, e -> clazz.cast(e.getValue())));
  }
}
