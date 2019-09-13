/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.exceptions;

/** Base class for all Annot8 runtime exceptions */
public class Annot8RuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message */
  public Annot8RuntimeException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause */
  public Annot8RuntimeException(final String message, final Throwable t) {
    super(message, t);
  }
}
