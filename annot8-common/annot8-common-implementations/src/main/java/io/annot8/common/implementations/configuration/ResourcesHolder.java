/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.annot8.common.utils.java.CollectionUtils;
import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

public class ResourcesHolder {

  private final Map<Resource, Collection<Settings>> resourcesToConfiguration =
      new LinkedHashMap<>();
  private final Map<Resource, String> resourcesToId = new HashMap<>();

  public ResourcesHolder addResource(
      final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesToConfiguration.put(resource, CollectionUtils.nonNullCollection(configuration));
    resourcesToId.put(resource, id);
    return this;
  }

  public Map<Resource, Collection<Settings>> getResourcesToConfiguration() {
    return resourcesToConfiguration;
  }

  public Map<Resource, String> getResourcesToId() {
    return resourcesToId;
  }

  public String getId(Resource resource) {
    return resourcesToId.get(resource);
  }
}
