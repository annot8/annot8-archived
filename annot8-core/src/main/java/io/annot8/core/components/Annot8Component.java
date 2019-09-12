/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

/** Base interface from which all other Annot8 components extend. */
public interface Annot8Component extends AutoCloseable {

  @Override
  default void close() {
    // Do nothing
  }
}
