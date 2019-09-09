/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.definitions;

import java.util.Set;
import java.util.function.Supplier;

import io.annot8.common.pipelines.elements.Merge;

public class MergeDefinition {

  private final Set<String> inputs;

  private final String output;

  private final Supplier<Merge> mergeSupplier;

  public MergeDefinition(Set<String> inputs, String output, Supplier<Merge> mergeSupplier) {
    this.inputs = inputs;
    this.output = output;
    this.mergeSupplier = mergeSupplier;
  }

  public Set<String> getInputs() {
    return inputs;
  }

  public String getOutput() {
    return output;
  }

  public Merge create() {
    return mergeSupplier.get();
  }
}
