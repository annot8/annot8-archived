/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.helpers.builders;

/**
 * Helper interface to indicate that a builder has a build method.
 *
 * @param <A> The object being built by this builder
 */
public interface WithBuild<A> {

  /**
   * Save content of the builder into an object
   *
   * @return the object being built by this builder
   */
  A build();
}
