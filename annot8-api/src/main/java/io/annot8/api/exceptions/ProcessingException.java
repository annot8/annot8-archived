/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.exceptions;

/** Generic exception thrown if there is an error during processing of a document. */
public class ProcessingException extends Annot8RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with the given message
   *
   * @param message the message for information
   */
  public ProcessingException(final String message) {
    super(message);
  }

  /**
   * Create an exception with the given message and a root cause
   *
   * @param message the message for information
   * @param t original throwable
   */
  public ProcessingException(final String message, final Throwable t) {
    super(message, t);
  }
}
