/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components.annotations;

import io.annot8.api.settings.Settings;

/**
 * Indicates the settings class used by a component.
 *
 * Should be used on the {@link io.annot8.api.components.Annot8ComponentDescriptor}
 */
public @interface SettingsClass {
  Class<? extends Settings> value();
}
