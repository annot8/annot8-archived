/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.bounds.Bounds;

/**
 * Denotes the annotation types which are processed by the component.
 *
 * <p>To simply use value and required are synonymous and either can be used. If both are used they
 * are merged into a combined list.
 *
 * <p>Optional types are not needed in order for the component to generate output, but they may
 * enhance the processing.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(ProcessesAnnotations.class)
public @interface ProcessesAnnotation {

  /**
   * Annotation type processed
   *
   * @return annotation types
   */
  String value();

  /**
   * Annotation bounds processed
   *
   * @return the bound classes (defaults to Bounds.class meaning any, but should be made as specific
   *     as possible)
   */
  Class<? extends Bounds> bounds() default Bounds.class;

  /**
   * Is the annotation required for the component to function?
   *
   * @return true if optional, default is false
   */
  boolean optional() default false;
}
