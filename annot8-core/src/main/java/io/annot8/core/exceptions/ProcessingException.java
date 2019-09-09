/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.exceptions;

/** Generic exception thrown if there is an error during processing of a document. */
public class ProcessingException extends Annot8Exception {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message */
  public ProcessingException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause */
  public ProcessingException(final String message, final Throwable t) {
    super(message, t);
  }
}
