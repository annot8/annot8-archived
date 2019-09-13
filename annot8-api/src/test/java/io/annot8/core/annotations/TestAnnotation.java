/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.annotations;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.properties.ImmutableProperties;

public class TestAnnotation implements Annotation {

  private final String id;
  private final String type;
  private final ImmutableProperties properties;
  private final Bounds bounds;
  private final String contentId;

  public TestAnnotation(
      String id, String type, ImmutableProperties properties, Bounds bounds, String contentId) {
    this.id = id;
    this.type = type;
    this.properties = properties;
    this.bounds = bounds;
    this.contentId = contentId;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  @Override
  public Bounds getBounds() {
    return bounds;
  }

  @Override
  public String getContentId() {
    return contentId;
  }
}
