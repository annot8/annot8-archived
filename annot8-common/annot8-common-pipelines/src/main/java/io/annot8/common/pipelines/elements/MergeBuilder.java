/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import java.util.function.Supplier;

import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.core.helpers.builders.WithBuild;

public interface MergeBuilder extends WithBuild<MergeDefinition> {

  MergeBuilder withInput(String key);

  MergeBuilder withOutput(String key);

  default MergeBuilder use(Class<Merge> clazz) {
    return use(
        () -> {
          try {
            return clazz.getConstructor().newInstance();
          } catch (Exception e) {
            return null;
          }
        });
  }

  default MergeBuilder use(Merge merge) {
    return use(() -> merge);
  }

  MergeBuilder use(Supplier<Merge> supplier);
}
