/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.exceptions;

/** Exception indicating a required resource has not been provided to a component */
public class MissingResourceException extends Annot8Exception {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message */
  public MissingResourceException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause */
  public MissingResourceException(final String message, final Throwable t) {
    super(message, t);
  }
}
