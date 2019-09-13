/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components;

import io.annot8.api.capabilities.Capabilities;
import io.annot8.api.context.Context;
import io.annot8.api.settings.Settings;

public interface Annot8ComponentDescriptor<T extends Annot8Component, S extends Settings> {
  void setName(String name);

  String getName();

  void setSettings(S settings);

  S getSettings();

  Capabilities capabilities();

  T create(Context context);
}
