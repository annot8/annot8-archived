package io.annot8.core.components.annotations;

import io.annot8.core.settings.Settings;

public @interface SettingsClass {
  Class<? extends Settings> value();
}
