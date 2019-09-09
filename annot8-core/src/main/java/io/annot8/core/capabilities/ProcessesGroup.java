/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Denotes the groups types which are processed by the component.
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
@Repeatable(ProcessesGroups.class)
public @interface ProcessesGroup {

  /**
   * Group types processed
   *
   * @return group types
   */
  String value();

  /**
   * Is the group required for the component to function?
   *
   * @return true if optional, default is false
   */
  boolean optional() default false;
}
