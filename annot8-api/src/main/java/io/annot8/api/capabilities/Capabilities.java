/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import java.util.stream.Stream;

public interface Capabilities {
  Stream<Capability> creates();

  default <T extends Capability> Stream<T> creates(Class<T> type) {
    return creates().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }

  Stream<Capability> processes();

  default <T extends Capability> Stream<T> processes(Class<T> type) {
    return processes().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }

  Stream<Capability> deletes();

  default <T extends Capability> Stream<T> deletes(Class<T> type) {
    return deletes().filter(c -> type.isAssignableFrom(c.getClass())).map(type::cast);
  }
}
