/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import io.annot8.core.bounds.Bounds;

public class AnnotationCapability {

  public static final String ANY_TYPE = "__any__";

  private final String type;
  private final Class<? extends Bounds> bounds;

  public AnnotationCapability(String type, Class<? extends Bounds> bounds) {
    this.type = type;
    this.bounds = bounds;
  }

  public String getType() {
    return type;
  }

  public Class<? extends Bounds> getBounds() {
    return bounds;
  }
}
