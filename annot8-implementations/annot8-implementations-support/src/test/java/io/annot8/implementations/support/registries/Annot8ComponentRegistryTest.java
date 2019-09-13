/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.support.registries;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.annot8.api.components.Annot8Component;

public class Annot8ComponentRegistryTest {

  private Annot8ComponentRegistry registry;

  @BeforeEach
  public void before() {
    Set<Class<? extends Annot8Component>> components = new HashSet<>();
    components.add(TestProcessor.class);
    components.add(TestSource.class);
    registry = new Annot8ComponentRegistry(components);
  }

  @Test
  public void testGetSources() {
    Assertions.assertNotNull(registry);
    Assertions.assertEquals(1, registry.getProcessors().count());
    Assertions.assertEquals(1, registry.getSources().count());
  }
}
