/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.helpers.builders;

/**
 * Helper interface to indicate that a builder can createContent an object based off an existing
 * object of the same type
 *
 * @param <E> The builder class
 * @param <A> The class to derive from
 */
public interface WithFromBuilder<E, A> {

  /**
   * Add the informaton from the provided param
   *
   * @param from the object to build from
   * @return builder based on an existing object
   */
  E from(final A from);
}
