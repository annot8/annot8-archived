/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components;

import io.annot8.api.capabilities.Capabilities;
import io.annot8.api.context.Context;
import io.annot8.api.settings.Settings;

/**
 * Descriptor for an Annot8 component
 *
 * <p>Descriptors provide contextual information about a component, for example a name and it's
 * configuration, as well as a method to create an instance of that component.
 *
 * <p>Descriptors should be serializable, as they can be used to recreate pipelines.
 *
 * @param <T> The Annot8 component described by this descriptor
 * @param <S> The settings used by this component, or {@link io.annot8.api.settings.NoSettings} if
 *     the component doesn't use settings
 */
public interface Annot8ComponentDescriptor<T extends Annot8Component, S extends Settings> {

  /** Set the name of this component */
  void setName(String name);

  /** Get the name of this component */
  String getName();

  /** Set the settings for this component */
  void setSettings(S settings);

  /** Get the settings for this component */
  S getSettings();

  /** Get the capabilities of the component, as configured with the current settings */
  Capabilities capabilities();

  /**
   * Create an instance of the component, with the current settings and using the provided context
   */
  T create(Context context);
}
