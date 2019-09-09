/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import io.annot8.common.utils.java.CollectionUtils;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.Settings;

public class ComponentHolder<T extends Annot8Component> {

  private final Map<T, Collection<Settings>> componentToConfiguration = new LinkedHashMap<>();

  public ComponentHolder add(final T t, final Collection<Settings> configuration) {
    componentToConfiguration.put(t, CollectionUtils.nonNullCollection(configuration));
    return this;
  }

  public Map<T, Collection<Settings>> getComponentToConfiguration() {
    return componentToConfiguration;
  }
}
