/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.definitions;

import java.util.Set;
import java.util.function.Supplier;

import io.annot8.common.pipelines.elements.Branch;

public class BranchDefinition {

  private final Set<String> outputs;

  private final String input;

  private final Supplier<Branch> branchSupplier;

  public BranchDefinition(String input, Set<String> outputs, Supplier<Branch> branchSupplier) {
    this.input = input;
    this.outputs = outputs;
    this.branchSupplier = branchSupplier;
  }

  public String getInput() {
    return input;
  }

  public Set<String> getOutputs() {
    return outputs;
  }

  public Branch create() {
    return branchSupplier.get();
  }
}
