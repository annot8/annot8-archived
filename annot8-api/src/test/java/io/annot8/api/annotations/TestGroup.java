/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.annotations;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.annot8.api.properties.ImmutableProperties;
import io.annot8.api.references.AnnotationReference;

public class TestGroup implements Group {

  private final String id;
  private final String type;
  private final ImmutableProperties properties;
  private final Map<String, Collection<AnnotationReference>> references;
  private final String role;

  public TestGroup(
      String id,
      String type,
      ImmutableProperties properties,
      Map<String, Collection<AnnotationReference>> references,
      String role) {
    this.id = id;
    this.type = type;
    this.properties = properties;
    this.references = references;
    this.role = role;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  @Override
  public Map<String, Stream<AnnotationReference>> getReferences() {
    return references
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().stream()));
  }

  @Override
  public Optional<String> getRole(Annotation annotation) {
    return Optional.of(role);
  }

  @Override
  public boolean containsAnnotation(Annotation annotation) {
    return false;
  }
}
