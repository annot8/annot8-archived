/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.context;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

/** Base context interface from which all context implementations extend. */
public interface Context {

  /** The settings for the component that this context is being passed to. */
  Stream<Settings> getSettings();

  /** Return a settings object as the given class. */
  default <T extends Settings> Optional<T> getSettings(final Class<T> clazz) {
    final Stream<Settings> stream = getSettings();

    return stream.filter(clazz::isInstance).map(clazz::cast).findFirst();
  }

  /**
   * Return a settings object as the given class, attempting to create a new settings object of that
   * class if the currently given settings are not of this class.
   *
   * @param clazz the setting clazz to create
   * @param defaultValue if non=null this will be returned if the settings are absensce. If null
   *     then will try to create a new instances of the Settings class
   * @return may be null (if defaultValue is null)
   */
  default <T extends Settings> T getSettings(final Class<T> clazz, T defaultValue) {

    return getSettings(clazz)
        .orElseGet(
            () -> {
              if (defaultValue != null) {
                return defaultValue;
              } else {
                try {
                  return clazz.getConstructor().newInstance();
                } catch (final Exception e) {
                  return null;
                }
              }
            });
  }

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
