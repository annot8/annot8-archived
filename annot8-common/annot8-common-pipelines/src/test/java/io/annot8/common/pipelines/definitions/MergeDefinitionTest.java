/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.definitions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.elements.Merge;

@ExtendWith(MockitoExtension.class)
class MergeDefinitionTest {

  Set<String> inputs = new HashSet<>();

  String output = "example";

  @Mock Supplier<Merge> supplier;

  @Mock Merge merge;

  @Test
  void constructor() {
    MergeDefinition defn = new MergeDefinition(inputs, output, supplier);

    assertThat(defn.getInputs()).containsExactlyElementsOf(inputs);
    assertThat(defn.getOutput()).isEqualTo(output);
  }

  @Test
  void create() {
    when(supplier.get()).thenReturn(merge);
    MergeDefinition defn = new MergeDefinition(inputs, output, supplier);

    Merge created = defn.create();
    assertThat(merge).isEqualTo(created);
  }
}
