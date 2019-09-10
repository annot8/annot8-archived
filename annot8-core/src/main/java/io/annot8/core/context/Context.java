/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.context;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

/** Base context interface from which all context implementations extend. */
public interface Context {

  /**
   * Find the resource of the given type associated with the given key
   *
   * @param key the key (if null / empty then any resource can be returned)
   * @param clazz the required resource class
   * @return resouce if found
   */
  <T extends Resource> Optional<T> getResource(final String key, final Class<T> clazz);

  /** List all the resource keys contained within this context */
  Stream<String> getResourceKeys();

  /** List all the resource keys contained within this context that are of the specified type */
  default Stream<String> getResourceKeys(final Class<? extends Resource> clazz) {
    return getResourceKeys().filter(s -> getResource(s, clazz).isPresent());
  }

  /** Return any resource (if there is one) of the specified type */
  default <T extends Resource> Optional<T> getResource(final Class<T> clazz) {
    return getResources(clazz).findFirst();
  }

  /** Return all resources of the specified type */
  <T extends Resource> Stream<T> getResources(final Class<T> clazz);
}
