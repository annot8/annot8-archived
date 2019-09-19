/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import java.util.stream.Stream;

/** Interface for capturing the capabilities of an Annot8 component */
public interface Capabilities {
  /**
   * Describes the things (e.g. content, annotations, groups) created by a component
   *
   * @return Stream of {@link Capability} objects describing what is created
   */
  Stream<Capability> creates();

  /**
   * Helper method for filtering {@link #creates()} to a specific type of Capability
   *
   * @param type The type of capability to filter to
   * @param <T> Class extending Capability
   */
  default <T extends Capability> Stream<T> creates(Class<T> type) {
    return creates().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }

  /**
   * Describes the things (e.g. content, annotations, groups) processed by a component
   *
   * @return Stream of {@link Capability} objects describing what is processed
   */
  Stream<Capability> processes();

  /**
   * Helper method for filtering {@link #processes()} to a specific type of Capability
   *
   * @param type The type of capability to filter to
   * @param <T> Class extending Capability
   */
  default <T extends Capability> Stream<T> processes(Class<T> type) {
    return processes().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }

  /**
   * Describes the things (e.g. content, annotations, groups) deleted by a component
   *
   * @return Stream of {@link Capability} objects describing what is deleted
   */
  Stream<Capability> deletes();

  /**
   * Helper method for filtering {@link #deletes()} to a specific type of Capability
   *
   * @param type The type of capability to filter to
   * @param <T> Class extending Capability
   */
  default <T extends Capability> Stream<T> deletes(Class<T> type) {
    return deletes().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }
}
