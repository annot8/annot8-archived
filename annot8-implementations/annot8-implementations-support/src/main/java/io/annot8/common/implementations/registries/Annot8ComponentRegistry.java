/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.registries;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import io.annot8.api.components.Annot8Component;
import io.annot8.api.components.Processor;
import io.annot8.api.components.Source;

public class Annot8ComponentRegistry {

  private final Set<Class<? extends Annot8Component>> classes;

  public Annot8ComponentRegistry(Set<Class<? extends Annot8Component>> classes) {
    this.classes = Collections.unmodifiableSet(classes);
  }

  public Stream<Class<? extends Source>> getSources() {
    return classes
        .stream()
        .filter(Source.class::isAssignableFrom)
        .map(c -> c.asSubclass(Source.class));
  }

  public Stream<Class<? extends Processor>> getProcessors() {
    return classes
        .stream()
        .filter(Processor.class::isAssignableFrom)
        .map(c -> c.asSubclass(Processor.class));
  }

  public Optional<Class<? extends Source>> getSource(String klass) {
    return getSources().filter(c -> c.getName().equals(klass)).findFirst();
  }

  public Optional<Class<? extends Processor>> getProcessor(String klass) {
    return getProcessors().filter(c -> c.getName().equals(klass)).findFirst();
  }

  public <T extends Annot8Component> Optional<Class<? extends T>> getComponent(
      String klass, Class<T> componentType) {
    return classes
        .stream()
        .filter(componentType::isAssignableFrom)
        .filter(c -> c.getName().equals(klass))
        .findFirst()
        .map(c -> c.asSubclass(componentType));
  }
}
