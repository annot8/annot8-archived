/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.annot8.core.data.Content;

/** Denotes that the component will delete content. */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(DeletesContents.class)
public @interface DeletesContent {

  /**
   * Type of content deleted
   *
   * @return the content class which will be deleted
   */
  Class<? extends Content<?>> value();
}
