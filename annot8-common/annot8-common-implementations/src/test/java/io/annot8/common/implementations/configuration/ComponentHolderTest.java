/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.annot8.common.data.tuple.Tuple2;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.settings.Settings;

class ComponentHolderTest {

  private ComponentHolder<Processor> holder;

  @BeforeEach
  public void beforeEach() {
    holder = new ComponentHolder<>();
  }

  @Test
  public void add() {

    fixtures().forEach(this::addProcessor);

    assertThat(holder.getComponents()).hasSize(3);
  }

  void addProcessor(Processor r) {
    holder.add(r);
  }

  static Stream<Processor> fixtures() {
    return Stream.of(
        new FakeProcessor(),
        new FakeProcessor(),
            new FakeProcessor());
  }

  public static class FakeProcessor implements Processor {

    @Override
    public ProcessorResponse process(Item item) throws Annot8Exception {
      return ProcessorResponse.ok();
    }
  }
}
