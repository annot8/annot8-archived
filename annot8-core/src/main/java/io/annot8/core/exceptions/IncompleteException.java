/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.exceptions;

/**
 * Exception indicating that a builder has been asked to createContent a new object without having been
 * given all the required information
 */
public class IncompleteException extends Annot8RuntimeException {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message */
  public IncompleteException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause */
  public IncompleteException(final String message, final Throwable t) {
    super(message, t);
  }
}
