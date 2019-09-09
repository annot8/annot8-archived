/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.bounds.Bounds;

/** Denotes the annotation types which the component will delete. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(DeletesAnnotations.class)
public @interface DeletesAnnotation {

  /**
   * Type of annotation deleted.
   *
   * @return annotation types
   */
  String value();

  /**
   * Bound of annotation deleted
   *
   * @return the bound classes
   */
  Class<? extends Bounds> bounds();
}
