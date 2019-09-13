/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.helpers.builders;

/**
 * Helper interface to indicate that a builder should processor setting a type on an object
 *
 * @param <A> The builder class
 */
public interface WithTypeBuilder<A> {

  /**
   * Assign a type to the builder
   *
   * @param type the type
   * @return a builder with the type set to the specified value
   */
  A withType(final String type);
}
