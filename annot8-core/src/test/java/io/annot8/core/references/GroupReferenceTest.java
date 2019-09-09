/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.references;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.annot8.core.annotations.Group;

class GroupReferenceTest {

  @Test
  void toGroupsWithNull() {
    assertThat(GroupReference.toGroups(null).count()).isEqualTo(0);
  }

  @Test
  void toGroups() {

    final Stream<GroupReference> input =
        Stream.of(createMockGroup(), createMockGroup(), createMockGroup(), createMissingGroup());

    final List<Group> output = GroupReference.toGroups(input).collect(Collectors.toList());

    assertThat(output).hasSize(3);
  }

  private GroupReference createMissingGroup() {
    GroupReference reference = Mockito.mock(GroupReference.class);
    when(reference.toGroup()).thenReturn(Optional.empty());
    return reference;
  }

  private GroupReference createMockGroup() {
    GroupReference reference = Mockito.mock(GroupReference.class);
    Group group = Mockito.mock(Group.class);
    when(reference.toGroup()).thenReturn(Optional.of(group));
    return reference;
  }
}
