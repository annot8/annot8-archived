/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components.annotations;

import io.annot8.api.settings.Settings;

public @interface SettingsClass {
  Class<? extends Settings> value();
}
