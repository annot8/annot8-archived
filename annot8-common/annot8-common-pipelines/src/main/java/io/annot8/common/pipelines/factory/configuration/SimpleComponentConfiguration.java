/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import io.annot8.core.settings.Settings;

public class SimpleComponentConfiguration implements ComponentConfiguration {

  private String name;
  private String component;
  private Set<Settings> settings = new HashSet<>();

  public SimpleComponentConfiguration() {
    // Do nothing
  }

  public SimpleComponentConfiguration(String component, Collection<Settings> settings) {
    this(null, component, settings);
  }

  public SimpleComponentConfiguration(
      String name, String component, Collection<Settings> settings) {
    this.name = name;
    this.component = component;
    this.settings.addAll(settings);
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getComponent() {
    return component;
  }

  public void setComponent(String component) {
    this.component = component;
  }

  @Override
  public Set<Settings> getSettings() {
    return settings;
  }

  public void setSettings(Set<Settings> settings) {
    this.settings = settings;
  }
}
