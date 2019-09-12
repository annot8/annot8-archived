/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.components.Resource;

/** Resources used by this component */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(ResourceKeys.class)
public @interface ResourceKey {
  String key();

  Class<? extends Resource> type();

  boolean optional() default false;
}
