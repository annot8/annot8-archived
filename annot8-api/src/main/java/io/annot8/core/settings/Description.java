/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.settings;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describes a parameter within a settings class.
 *
 * <p>This should be set on the get method for a parameter, and it may generally be assumed that
 * there is a corresponding set method.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Description {

  /**
   * A description of this parameter
   *
   * @return description
   */
  String description();

  /**
   * Default value of this parameter
   *
   * @return default value of this parameter
   */
  String defaultValue() default "";
}
