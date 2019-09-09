/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** Denotes the group types the component may output. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(CreatesGroups.class)
public @interface CreatesGroup {

  /**
   * Type of group created
   *
   * @return group types
   */
  String value();
}
