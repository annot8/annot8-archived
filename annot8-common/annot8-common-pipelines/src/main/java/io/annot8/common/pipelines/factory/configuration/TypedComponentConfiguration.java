/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.Collection;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.Settings;

public class TypedComponentConfiguration<T extends Annot8Component> {

  private final Class<? extends T> componentClass;
  private final Settings settings;

  public TypedComponentConfiguration(
      Class<? extends T> componentClass, Settings settings) {
    this.componentClass = componentClass;
    this.settings = settings;
  }

  public Settings getSettings() {
    return settings;
  }

  public Class<? extends T> getComponentClass() {
    return componentClass;
  }
}
