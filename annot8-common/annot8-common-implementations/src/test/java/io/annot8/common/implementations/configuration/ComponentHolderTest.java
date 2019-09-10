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

    fixtures().forEach(t -> addProcessor(t.getA(), t.getB()));

    assertThat(holder.getComponentToConfiguration()).hasSize(3);
  }

  void addProcessor(Processor r, Collection<Settings> settings) {

    holder.add(r, settings);

    Collection<Settings> expectedSettings = settings == null ? Collections.emptyList() : settings;
    assertThat(holder.getComponentToConfiguration().get(r))
        .containsExactlyElementsOf(expectedSettings);
  }

  static Stream<Tuple2<Processor, Collection<Settings>>> fixtures() {
    return Stream.of(
        new Tuple2<>(new FakeProcessor(), null),
        new Tuple2<>(new FakeProcessor(), Collections.emptyList()),
        new Tuple2<>(
            new FakeProcessor(),
            Arrays.asList(new ResourcesHolderTest.FakeSettings(), new FakeSettings())));
  }

  public static class FakeSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public static class FakeProcessor implements Processor {

    @Override
    public ProcessorResponse process(Item item) {
      return ProcessorResponse.ok();
    }
  }
}
