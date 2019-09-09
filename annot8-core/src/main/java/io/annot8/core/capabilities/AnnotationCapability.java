/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.Objects;

import io.annot8.core.bounds.Bounds;

/**
 * Specification for annotation created, processed or deleted by a component.
 *
 * <p>Note that optional will be set to true for create and delete operations. This reflects that
 * typically we may or may not create/delete elements, even if all the prerequisites are met.
 */
public class AnnotationCapability {

  private final String type;

  private final Class<? extends Bounds> bounds;

  private final boolean optional;

  /**
   * Create from {@link CreatesAnnotation}
   *
   * @param annotation the annotation
   */
  public AnnotationCapability(CreatesAnnotation annotation) {
    this(annotation.value(), annotation.bounds(), true);
  }

  /**
   * Create from {@link DeletesAnnotation}
   *
   * @param annotation the annotation
   */
  public AnnotationCapability(DeletesAnnotation annotation) {
    this(annotation.value(), annotation.bounds(), true);
  }

  /**
   * Create from {@link ProcessesAnnotation}
   *
   * @param annotation the annotation
   */
  public AnnotationCapability(ProcessesAnnotation annotation) {
    this(annotation.value(), annotation.bounds(), annotation.optional());
  }

  /**
   * Create from fields
   *
   * @param type the annotation type
   * @param bounds the annotation bounds
   * @param optional whether the above are required (processing specific)
   */
  public AnnotationCapability(String type, Class<? extends Bounds> bounds, boolean optional) {
    this.type = type;
    this.bounds = bounds;
    this.optional = optional;
  }

  /**
   * Get the annotation type created/deleted/processed
   *
   * @return annotation type
   */
  public String getType() {
    return type;
  }

  /**
   * Get the bounds created/deleted/processed
   *
   * @return the class of bounds
   */
  public Class<? extends Bounds> getBounds() {
    return bounds;
  }

  /**
   * Is the annotation defined by this object optionally (required for processing, created, deleted)
   *
   * @return true if optional
   */
  public boolean isOptional() {
    return optional;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnnotationCapability that = (AnnotationCapability) o;
    return optional == that.optional
        && Objects.equals(type, that.type)
        && Objects.equals(bounds, that.bounds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, bounds, optional);
  }
}
