package io.annot8.core.components.annotations;

import io.annot8.core.components.Resource;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Resources used by this component
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(ResourceKeys.class)
public @interface ResourceKey {
  String key();
  Class<? extends Resource> type();
  boolean optional() default false;
}
