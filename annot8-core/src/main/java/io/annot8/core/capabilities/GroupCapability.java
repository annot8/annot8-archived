/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.Objects;

/**
 * Specification for group created, processed or deleted by a component.
 *
 * <p>Note that optional will be set to true for createContent and delete operations. This reflects that
 * typically we may or may not createContent/delete elements, even if all the prerequisites are met.
 */
public class GroupCapability {

  private final String type;

  private final boolean optional;

  /**
   * Create from details
   *
   * @param type the group type
   * @param optional true if the resource optional
   */
  public GroupCapability(String type, boolean optional) {
    this.type = type;
    this.optional = optional;
  }
  /**
   * Create from {@link CreatesGroup}
   *
   * @param annotation specification
   */
  public GroupCapability(CreatesGroup annotation) {
    this(annotation.value(), true);
  }

  /**
   * Create from {@link DeletesGroup}
   *
   * @param annotation specification
   */
  public GroupCapability(DeletesGroup annotation) {
    this(annotation.value(), true);
  }

  /**
   * Create from {@link ProcessesGroup}
   *
   * @param annotation specification
   */
  public GroupCapability(ProcessesGroup annotation) {
    this(annotation.value(), annotation.optional());
  }

  /**
   * Get the group type created/deleted/processed
   *
   * @return group type
   */
  public String getType() {
    return type;
  }

  /**
   * Is the group defined by this object optionally (required for processing, created, deleted)
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
    GroupCapability that = (GroupCapability) o;
    return optional == that.optional && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, optional);
  }
}
