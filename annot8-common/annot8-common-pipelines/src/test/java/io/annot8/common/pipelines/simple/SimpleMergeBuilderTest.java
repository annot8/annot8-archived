/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.core.exceptions.IncompleteException;

@ExtendWith(MockitoExtension.class)
class SimpleMergeBuilderTest {

  @Mock Merge merge;

  @Test
  void build() throws IncompleteException {

    MergeDefinition build =
        new SimpleMergeBuilder().withInput("in").withOutput("out").use(merge).build();

    assertThat(build.getInputs()).containsExactlyInAnyOrder("in");
    assertThat(build.getOutput()).isEqualTo("out");
    assertThat(build.create()).isEqualTo(merge);
  }
}
