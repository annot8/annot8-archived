/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import java.util.*;

import io.annot8.common.utils.java.CollectionUtils;
import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

public class ResourcesHolder {

  private final Map<Resource, String> resourcesToId = new HashMap<>();

  public ResourcesHolder addResource(
      final String id, final Resource resource) {
    resourcesToId.put(resource, id);
    return this;
  }

  public Set<Resource> getResources() {
    return Collections.unmodifiableSet(resourcesToId.keySet());
  }

  public Map<Resource, String> getResourcesToId() {
    return resourcesToId;
  }

  public String getId(Resource resource) {
    return resourcesToId.get(resource);
  }
}
