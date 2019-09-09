/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.bounds.Bounds;

/** Denotes the annotation types which the component will output. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(CreatesAnnotations.class)
public @interface CreatesAnnotation {

  /**
   * Type of annotation created.
   *
   * @return annotation type
   */
  String value();

  /**
   * Bounds of annotation generated
   *
   * @return the bound classes
   */
  Class<? extends Bounds> bounds();
}
