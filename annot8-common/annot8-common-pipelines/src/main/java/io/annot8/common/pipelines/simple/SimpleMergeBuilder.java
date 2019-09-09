/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.common.pipelines.elements.MergeBuilder;
import io.annot8.core.exceptions.IncompleteException;

public class SimpleMergeBuilder implements MergeBuilder {

  private Set<String> inputs = new HashSet<>();
  private String output;

  // Default to a simple merge
  private Supplier<Merge> supplier = SimpleMerge::new;

  @Override
  public MergeBuilder withInput(String key) {
    inputs.add(key);
    return this;
  }

  @Override
  public MergeBuilder withOutput(String key) {
    output = key;
    return this;
  }

  @Override
  public MergeBuilder use(Supplier<Merge> branch) {
    supplier = branch;
    return this;
  }

  @Override
  public MergeDefinition build() throws IncompleteException {
    Objects.requireNonNull(output);
    Objects.requireNonNull(supplier);

    return new MergeDefinition(inputs, output, supplier);
  }
}
