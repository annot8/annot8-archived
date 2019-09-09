/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.Objects;
import java.util.Optional;

import io.annot8.core.annotations.Group;
import io.annot8.core.references.GroupReference;

public class TestGroupReference implements GroupReference {

  private Group group;

  public TestGroupReference(Group group) {
    if (group == null) {
      throw new IllegalArgumentException("Invalid null group");
    }
    this.group = group;
  }

  @Override
  public String getGroupId() {
    return group.getId();
  }

  @Override
  public Optional<Group> toGroup() {
    return Optional.of(group);
  }

  public void setGroup(Group group) {
    if (group == null) {
      throw new IllegalArgumentException("Invalid null group");
    }
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestGroupReference that = (TestGroupReference) o;

    return Objects.equals(group.getId(), that.group.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(group.getId());
  }
}
