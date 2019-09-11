package io.annot8.core.components.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Human readable component description
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ComponentDescription {
  String value();
}
