/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** Human readable component name, where it differs from the class name */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ComponentName {
  String value();
}
