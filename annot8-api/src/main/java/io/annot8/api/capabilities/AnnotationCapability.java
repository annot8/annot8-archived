/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import io.annot8.api.bounds.Bounds;

/** Describes an annotation that is created, processed or deleted */
public interface AnnotationCapability extends Capability {
  /** The type of annotation */
  String getType();

  /** The bounds of the annotation */
  Class<? extends Bounds> getBounds();
}
