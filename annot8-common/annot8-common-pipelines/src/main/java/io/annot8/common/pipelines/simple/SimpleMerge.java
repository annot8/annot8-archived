/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.Objects;
import java.util.function.Predicate;

import io.annot8.common.pipelines.elements.Merge;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public class SimpleMerge implements Merge {

  private WithProcessItem consumer;
  private final Predicate<Item> predicate;

  public SimpleMerge() {
    this(null);
  }

  public SimpleMerge(Predicate<Item> predicate) {
    this.predicate = predicate;
  }

  @Override
  public boolean receive(Item item) throws Annot8Exception {
    if (predicate != null && !predicate.test(item)) {
      return false;
    }

    if (consumer != null) {
      final ProcessorResponse response = consumer.process(item);
      return response.getStatus() == Status.OK;
    }

    return false;
  }

  @Override
  public void setOutput(WithProcessItem pipe) {
    Objects.requireNonNull(pipe);
    this.consumer = pipe;
  }

  @Override
  public void close() {
    // Do nothing
  }
}
