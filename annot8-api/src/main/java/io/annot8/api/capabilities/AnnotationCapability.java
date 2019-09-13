/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import io.annot8.api.bounds.Bounds;

public interface AnnotationCapability extends Capability {
  String getType();

  Class<? extends Bounds> getBounds();
}
