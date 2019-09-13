/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.context;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;

public class SimpleContext implements Context {

  private final Collection<Resource> resources;

  public SimpleContext(Resource... resources) {
    this(Arrays.asList(resources));
  }

  public SimpleContext(Collection<Resource> resources) {
    this.resources = resources == null ? Collections.emptyList() : resources;
  }

  @Override
  public Stream<Resource> getResources() {
    return resources.stream();
  }
}
