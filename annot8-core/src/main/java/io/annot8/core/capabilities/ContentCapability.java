/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.Objects;

import io.annot8.core.data.Content;

/**
 * Specification for content created, processed or deleted by a component.
 *
 * <p>Note that optional will be set to true for createContent and delete operations. This reflects that
 * typically we may or may not createContent/delete elements, even if all the prerequisites are met.
 */
public class ContentCapability {

  private final Class<? extends Content<?>> type;

  private final boolean optional;

  /**
   * Create from {@link CreatesContent}
   *
   * @param annotation specification
   */
  public ContentCapability(CreatesContent annotation) {
    this(annotation.value(), true);
  }

  /**
   * Create from {@link DeletesContent}
   *
   * @param annotation specification
   */
  public ContentCapability(DeletesContent annotation) {
    this(annotation.value(), true);
  }

  /**
   * Create from {@link ProcessesContent}
   *
   * @param annotation specification
   */
  public ContentCapability(ProcessesContent annotation) {
    this(annotation.value(), annotation.optional());
  }

  /**
   * Create from details
   *
   * @param type the resource type
   * @param optional true if the resource optional
   */
  public ContentCapability(Class<? extends Content<?>> type, boolean optional) {
    this.type = type;
    this.optional = optional;
  }

  /**
   * Get the content type created/deleted/processed
   *
   * @return content type
   */
  public Class<? extends Content<?>> getType() {
    return type;
  }

  /**
   * Is the content defined by this object optionally (required for processing, created, deleted)
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
    ContentCapability that = (ContentCapability) o;
    return optional == that.optional && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, optional);
  }
}
