/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import io.annot8.core.bounds.Bounds;

public interface AnnotationCapability extends Capability {
  String getType();

  Class<? extends Bounds> getBounds();
}
