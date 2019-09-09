/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.settings;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify which class is to be used for settings by a component.
 *
 * <p>Note that whilst from a configuration perspective the annotation is inherited (ie you can
 * configure the parent, from a Java perspective is it not - the settings class applies to the class
 * they are defined on)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(SettingsClasses.class)
public @interface SettingsClass {

  /**
   * The class used by this component to hold it's settings
   *
   * @return settings class
   */
  Class<? extends Settings> value();

  /**
   * Is the content required for the component to function?
   *
   * @return true if optional, default is false
   */
  boolean optional() default false;
}
