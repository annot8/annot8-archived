/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** Denotes the group types the component may delete. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(DeletesGroups.class)
public @interface DeletesGroup {

  /**
   * Type of group deleted
   *
   * @return group types
   */
  String value();
}
