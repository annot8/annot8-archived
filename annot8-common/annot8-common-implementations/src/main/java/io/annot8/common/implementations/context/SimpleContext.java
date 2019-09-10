/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.settings.Settings;

/** Simple implementation of Context, backed by a HashMap to store resources */
public class SimpleContext implements Context {

  private final Map<String, Resource> resources = new HashMap<>();

  /** Create a new instance, with no resources */
  public SimpleContext() {
    this(null);
  }

  /** Create a new instance, with the resources */
  public SimpleContext(Map<String, Resource> resources) {
    if (resources != null) {
      this.resources.putAll(resources);
    }
  }

  /** Add a new resource to the context object */
  public void addResource(String key, Resource resource) {
    resources.put(key, resource);
  }

  @Override
  public <T extends Resource> Optional<T> getResource(String s, Class<T> aClass) {
    Resource r = resources.get(s);

    if (r == null || !aClass.isAssignableFrom(r.getClass())) {
      return Optional.empty();
    } else {
      return Optional.of(aClass.cast(r));
    }
  }

  @Override
  public Stream<String> getResourceKeys() {
    return resources.keySet().stream();
  }

  @Override
  public <T extends Resource> Stream<T> getResources(Class<T> aClass) {
    return resources
        .values()
        .stream()
        .filter(r -> aClass.isAssignableFrom(r.getClass()))
        .map(aClass::cast);
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [resources=" + resources + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash( resources);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Context)) {
      return false;
    }

    Context c = (Context) obj;

    Map<String, Resource> resourceMap = new HashMap<>();
    c.getResourceKeys()
        .forEach(
            s -> {
              Optional<Resource> r = c.getResource(s, Resource.class);
              r.ifPresent(resource -> resourceMap.put(s, resource));
            });

    return Objects.equals(resourceMap, this.resources);
  }
}
