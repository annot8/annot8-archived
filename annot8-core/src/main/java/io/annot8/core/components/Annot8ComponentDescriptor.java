package io.annot8.core.components;

import io.annot8.core.capabilities.ComponentCapabilities;
import io.annot8.core.settings.Settings;

public interface Annot8ComponentDescriptor<T extends Annot8Component, S extends Settings> {
  void setName(String name);
  String getName();

  void setSettings(S settings);
  S getSettings();

  ComponentCapabilities capabilities();
  T create();
}
