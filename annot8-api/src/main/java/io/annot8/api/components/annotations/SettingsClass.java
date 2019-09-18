/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.api.settings.Settings;

/**
 * Indicates the settings class used by a component.
 *
 * <p>Should be used on the {@link io.annot8.api.components.Annot8ComponentDescriptor}
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface SettingsClass {
  Class<? extends Settings> value();
}
