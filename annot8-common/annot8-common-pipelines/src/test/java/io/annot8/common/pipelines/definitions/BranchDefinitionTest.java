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

import io.annot8.common.pipelines.elements.Branch;

@ExtendWith(MockitoExtension.class)
class BranchDefinitionTest {

  Set<String> outputs = new HashSet<>();

  String input = "example";

  @Mock Supplier<Branch> branchSupplier;

  @Mock Branch branch;

  @Test
  void constructor() {
    BranchDefinition defn = new BranchDefinition(input, outputs, branchSupplier);

    assertThat(defn.getInput()).isEqualTo(input);
    assertThat(defn.getOutputs()).containsExactlyElementsOf(outputs);
  }

  @Test
  void create() {
    when(branchSupplier.get()).thenReturn(branch);
    BranchDefinition defn = new BranchDefinition(input, outputs, branchSupplier);

    Branch created = defn.create();
    assertThat(created).isEqualTo(branch);
  }
}
