/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.references;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.annotations.Group;

/**
 * A reference to a group.
 *
 * <p>This is a more lightweight object than the group.
 */
public interface GroupReference {

  /**
   * Convert a stream of group references to groups
   *
   * @param references reference to convert (may be null)
   * @return groups
   */
  static Stream<Group> toGroups(Stream<GroupReference> references) {
    if (references == null) {
      return Stream.empty();
    }

    return references.map(GroupReference::toGroup).filter(Optional::isPresent).map(Optional::get);
  }

  /**
   * Get the id of the group which is referenced
   *
   * @return group id
   */
  String getGroupId();

  /**
   * Convert a reference to a group instance.
   *
   * <p>Since the group may have been deleted, this returns an optional.
   *
   * @return group corresponding to this reference (if it exists)
   */
  Optional<Group> toGroup();
}
