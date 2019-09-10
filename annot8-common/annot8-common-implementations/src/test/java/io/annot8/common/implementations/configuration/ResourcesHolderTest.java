/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import io.annot8.common.data.tuple.Tuple2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.annot8.common.data.tuple.Tuple3;
import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

class ResourcesHolderTest {

  private ResourcesHolder holder;

  @BeforeEach
  public void beforeEach() {
    holder = new ResourcesHolder();
  }

  @Test
  public void addResources() {

    resourceFixtures().forEach(t -> addResource(t.getA(), t.getB()));

    assertThat(holder.getResources()).hasSize(3);
    assertThat(holder.getResourcesToId()).hasSize(3);
  }

  void addResource(String id, Resource r) {

    holder.addResource(id, r);

    assertThat(holder.getId(r)).isEqualTo(id);

    assertThat(holder.getResourcesToId()).containsEntry(r, id);

  }

  static Stream<Tuple2<String, Resource>> resourceFixtures() {
    return Stream.of(
        new Tuple2<>("id1", new FakeResource()),
        new Tuple2<>("id2", new FakeResource()),
        new Tuple2<>(
            "id3", new FakeResource()));
  }

  public static class FakeResource implements Resource {}
}
