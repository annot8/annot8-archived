/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.helpers;

/** Helper interface to indicate that a class has a type */
public interface WithType {

  /**
   * Get the type
   *
   * @return the type assigned to this object
   */
  String getType();
}
