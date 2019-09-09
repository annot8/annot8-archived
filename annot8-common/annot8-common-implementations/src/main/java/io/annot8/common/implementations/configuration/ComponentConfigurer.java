/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.context.MergedContext;
import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.settings.Settings;

public class ComponentConfigurer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComponentConfigurer.class);
  private final Context globalContext;

  private Map<String, Resource> resources = new HashMap<>();

  public ComponentConfigurer(Context context) {
    this.globalContext = context;

    // Add the global resources to our resource pool
    this.globalContext
        .getResourceKeys()
        .forEach(
            k -> globalContext.getResource(k, Resource.class).ifPresent(r -> resources.put(k, r)));
  }

  public Map<String, Resource> configureResources(ResourcesHolder resourcesHolder) {
    resourcesHolder
        .getResourcesToConfiguration()
        .forEach(
            (resource, settings) -> {
              if (configureComponent(resource, settings)) {
                String id = resourcesHolder.getId(resource);
                resources.put(id, resource);
              }
            });

    return getResources();
  }

  public Map<String, Resource> getResources() {
    return resources;
  }

  public <T extends Annot8Component> List<T> configureComponents(ComponentHolder<T> holder) {
    return configureAllComponents(holder.getComponentToConfiguration());
  }

  protected <T extends Annot8Component> List<T> configureAllComponents(
      Map<T, Collection<Settings>> componentToConfiguration) {

    return componentToConfiguration
        .entrySet()
        .stream()
        .filter(e -> configureComponent(e.getKey(), e.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public <T extends Annot8Component> boolean configureComponent(
      T component, Collection<Settings> settings) {

    // TODO: COmpletely ignore capabilties here.. we could check for resources etc

    try {
      final SimpleContext componentContext = new SimpleContext(settings, resources);
      Context context = new MergedContext(globalContext, componentContext);
      component.configure(context);
      return true;
    } catch (final Exception e) {
      LOGGER.error("Failed to configure component {}", component.getClass().getName(), e);
    }
    return false;
  }
}
