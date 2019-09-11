package io.annot8.core.capabilities;

import io.annot8.core.bounds.Bounds;

public class AnnotationCapability {
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
