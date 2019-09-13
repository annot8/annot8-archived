/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.annotations;

import java.util.Optional;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithProperties;
import io.annot8.core.helpers.WithType;
import io.annot8.core.helpers.builders.WithFromBuilder;
import io.annot8.core.helpers.builders.WithIdBuilder;
import io.annot8.core.helpers.builders.WithNewIdBuilder;
import io.annot8.core.helpers.builders.WithPropertiesBuilder;
import io.annot8.core.helpers.builders.WithSave;
import io.annot8.core.helpers.builders.WithTypeBuilder;

/** Base annotation interface from which all other annotations extend. */
public interface Annotation extends WithId, WithType, WithProperties {

  /**
   * Get the {@link Bounds} associated with this annotation
   *
   * @return the bounds
   */
  Bounds getBounds();

  /**
   * Get the {@link Bounds} associated with this annotation casting it to the bounds provided (if it
   * is of that type / subtype).
   *
   * @param boundsClass the class the bounds should have
   * @return optional bounds of requested class, empty if the bounds are not of that class
   */
  @SuppressWarnings("unchecked")
  default <B extends Bounds> Optional<B> getBounds(Class<B> boundsClass) {
    Bounds bounds = getBounds();
    if (boundsClass.isInstance(bounds)) {
      // This is checked
      return Optional.of((B) bounds);
    }
    return Optional.empty();
  }

  /**
   * Get the id of the Content to which this annotation refers
   *
   * @return content id
   */
  String getContentId();

  /**
   * Do two instances represent the same underlying annotations?
   *
   * <p>That is do they have the same id, even if the rest of the data is different.
   *
   * @param other the annotation to test against
   * @return true is non-null and have the same id
   */
  default boolean sameAnnotation(Annotation other) {
    return other != null && getId().equals(other.getId());
  }

  /** Builder interface to createContent (immutable) Annotation classes */
  interface Builder
      extends WithTypeBuilder<Annotation.Builder>,
          WithIdBuilder<Annotation.Builder>,
          WithPropertiesBuilder<Annotation.Builder>,
          WithNewIdBuilder<Annotation.Builder>,
          WithFromBuilder<Builder, Annotation>,
          WithSave<Annotation> {

    /**
     * Set the {@link Bounds} associated with this annotation
     *
     * @param bounds the bounds of the annotation
     * @return the builder for chaining
     */
    Annotation.Builder withBounds(final Bounds bounds);
  }
}
