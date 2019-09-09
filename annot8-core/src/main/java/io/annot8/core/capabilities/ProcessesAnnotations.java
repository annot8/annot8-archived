/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Wrapper for repeated {@link ProcessesAnnotation}.
 *
 * <p>Developers do not need to use this (it is used internally by java to autowrap multiple
 * annotations).
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ProcessesAnnotations {

  /**
   * Get the array of annotations
   *
   * @return the repeated annotations
   */
  ProcessesAnnotation[] value();
}
