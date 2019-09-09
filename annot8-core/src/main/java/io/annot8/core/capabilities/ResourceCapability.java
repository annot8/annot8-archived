/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.Objects;

import io.annot8.core.components.Resource;

/**
 * Specification for resource used by a component.
 *
 * <p>Note that optional will be set to true for create and delete operations. This reflects that
 * typically we may or may not create/delete elements, even if all the prerequisites are met.
 */
public class ResourceCapability {

  private final Class<? extends Resource> type;

  private final boolean optional;

  /**
   * Create from details
   *
   * @param type the resource type
   * @param optional true if the resource optional
   */
  public ResourceCapability(Class<? extends Resource> type, boolean optional) {
    this.type = type;
    this.optional = optional;
  }

  /**
   * Create from {@link UsesResource}.
   *
   * @param annotation specification
   */
  public ResourceCapability(UsesResource annotation) {
    this(annotation.value(), annotation.optional());
  }

  /**
   * Get the resource type created/deleted/processed
   *
   * @return resource type
   */
  public Class<? extends Resource> getType() {
    return type;
  }

  /**
   * Is the resource defined by this object optionally used (ie used is available)
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
    ResourceCapability that = (ResourceCapability) o;
    return optional == that.optional && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, optional);
  }
}
