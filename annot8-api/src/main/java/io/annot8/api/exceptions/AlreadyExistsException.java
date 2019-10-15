/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.exceptions;

/**
 * Exception indicating that an attempt has been made to createContent something that already
 * exists, i.e. has the same ID.
 */
public class AlreadyExistsException extends Annot8RuntimeException {

  private static final long serialVersionUID = 1L;

  /** Create an exception with the given message
 *  @param message the message for info
*/
  public AlreadyExistsException(final String message) {
    super(message);
  }

  /** Create an exception with the given message and a root cause
   *  @param message the message for info
   *  @param t original exception   */
  public AlreadyExistsException(final String message, final Throwable t) {
    super(message, t);
  }
}
