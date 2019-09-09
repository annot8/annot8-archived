/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.data.Content;

/** Denotes that the component will generate content. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(CreatesContents.class)
public @interface CreatesContent {

  /**
   * Content class to be created
   *
   * @return the content class which will be generated
   */
  Class<? extends Content<?>> value();
}
