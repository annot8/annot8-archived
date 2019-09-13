/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.context.Context;
import io.annot8.core.settings.Settings;

public interface Annot8ComponentDescriptor<T extends Annot8Component, S extends Settings> {
  void setName(String name);

  String getName();

  void setSettings(S settings);

  S getSettings();

  Capabilities capabilities();

  T create(Context context);
}
