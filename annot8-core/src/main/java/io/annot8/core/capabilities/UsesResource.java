/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.components.Resource;

/** The resources which this component may or will use. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(UsesResources.class)
public @interface UsesResource {

  /**
   * Resources class used.
   *
   * @return resource class
   */
  Class<? extends Resource> value();

  /**
   * Is the resource required for the component to function?
   *
   * @return true if optional, default is false
   */
  boolean optional() default false;
}
