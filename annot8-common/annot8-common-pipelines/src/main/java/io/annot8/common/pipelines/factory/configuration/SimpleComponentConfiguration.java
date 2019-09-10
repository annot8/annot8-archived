/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.HashSet;

import io.annot8.core.settings.Settings;

public class SimpleComponentConfiguration implements ComponentConfiguration {

  private String name;
  private String component;
  private Settings settings;

  public SimpleComponentConfiguration() {
    // Do nothing
  }

  public SimpleComponentConfiguration(String component, Settings settings) {
    this(null, component, settings);
  }

  public SimpleComponentConfiguration(
      String name, String component, Settings settings) {
    this.name = name;
    this.component = component;
    this.settings = settings;
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
  public Settings getSettings() {
    return settings;
  }

  public void setSettings(Settings settings) {
    this.settings = settings;
  }
}
