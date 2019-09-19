/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.context;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.api.components.Resource;

/**
 * A context defines additional information and resources for the environment in which the
 * components are running.
 */
public interface Context {

  /**
   * Get all the resources
   *
   * @return stream of resources
   */
  Stream<Resource> getResources();

  /**
   * Get all the resources of that class.
   *
   * @param resourceClass the resource class required
   * @param <R> the resource class to return
   * @return stream of matching resources
   */
  default <R> Stream<R> getResources(Class<R> resourceClass) {
    return getResources().filter(resourceClass::isInstance).map(resourceClass::cast);
  }

  /**
   * Get a single resource which matches.
   *
   * <p>If there are multiple matches the underlying implementation may provide any back.
   *
   * @param resourceClass the resource class required
   * @param <R> the resource class to return
   * @return optional of matching resources
   */
  default <R> Optional<R> getResource(Class<R> resourceClass) {
    return getResources(resourceClass).findAny();
  }
}
