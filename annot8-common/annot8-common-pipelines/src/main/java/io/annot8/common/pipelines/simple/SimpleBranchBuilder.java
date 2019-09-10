/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.BranchBuilder;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.IncompleteException;

public class SimpleBranchBuilder implements BranchBuilder {

  private final Set<String> outputs = new HashSet<>();
  private String input;
  private Supplier<Branch> supplier;

  @Override
  public BranchBuilder withInput(String key) {
    input = key;
    return this;
  }

  @Override
  public BranchBuilder withOutput(String key) {
    outputs.add(key);
    return this;
  }

  @Override
  public BranchBuilder use(Supplier<Branch> branch) {
    supplier = branch;
    return this;
  }

  public BranchBuilder use(Function<Item, String> decider) {
    use(new SimpleBranch(decider));
    return this;
  }

  @Override
  public BranchDefinition build() throws IncompleteException {
    Objects.requireNonNull(input);
    Objects.requireNonNull(supplier);

    return new BranchDefinition(input, outputs, supplier);
  }
}
