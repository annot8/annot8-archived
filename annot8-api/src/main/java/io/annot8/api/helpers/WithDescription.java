/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.helpers;

/** Helper interface to indicate that a class has a human readable description */
public interface WithDescription {

  /**
   * Get the description
   *
   * @return the description assigned to this object
   */
  String getDescription();
}
