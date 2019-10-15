/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import io.annot8.api.bounds.Bounds;

/** Describes an annotation that is created, processed or deleted */
public interface AnnotationCapability extends Capability {
  /** The type of annotation
   *
   * @return the type
   */
  String getType();

  /** The bounds of the annotation
   * @return the bounds class
   */
  Class<? extends Bounds> getBounds();
}
