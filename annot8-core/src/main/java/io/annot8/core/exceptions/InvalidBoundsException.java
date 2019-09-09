/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.exceptions;

/**
 * Runtime exception indicating that an attempt to either create or use an invalid Bounds object has
 * been performed.
 */
public class InvalidBoundsException extends Annot8RuntimeException {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message */
  public InvalidBoundsException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause */
  public InvalidBoundsException(final String message, final Throwable t) {
    super(message, t);
  }
}
