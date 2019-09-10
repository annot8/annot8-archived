/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

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

    resourceFixtures().forEach(t -> addResource(t.getA(), t.getB(), t.getC()));

    assertThat(holder.getResources()).hasSize(3);
    assertThat(holder.getResourcesToId()).hasSize(3);
  }

  void addResource(String id, Resource r, Collection<Settings> settings) {

    holder.addResource(id, r, settings);

    assertThat(holder.getId(r)).isEqualTo(id);

    assertThat(holder.getResourcesToId()).containsEntry(r, id);

    Collection<Settings> expectedSettings = settings == null ? Collections.emptyList() : settings;
    assertThat(holder.getResources().get(r))
        .containsExactlyElementsOf(expectedSettings);
  }

  static Stream<Tuple3<String, Resource, Collection<Settings>>> resourceFixtures() {
    return Stream.of(
        new Tuple3<>("id1", new FakeResource(), null),
        new Tuple3<>("id2", new FakeResource(), Collections.emptyList()),
        new Tuple3<>(
            "id3", new FakeResource(), Arrays.asList(new FakeSettings(), new FakeSettings())));
  }

  public static class FakeSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public static class FakeResource implements Resource {}
}
