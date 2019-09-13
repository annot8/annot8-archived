/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.data;

import java.util.function.Supplier;

import io.annot8.api.helpers.WithDescription;
import io.annot8.api.helpers.WithId;
import io.annot8.api.helpers.WithProperties;
import io.annot8.api.helpers.builders.WithFromBuilder;
import io.annot8.api.helpers.builders.WithIdBuilder;
import io.annot8.api.helpers.builders.WithPropertiesBuilder;
import io.annot8.api.helpers.builders.WithSave;
import io.annot8.api.stores.AnnotationStore;

/**
 * Base content interface from which all content implementations extend.
 *
 * @param <D> the type of data held
 */
public interface Content<D> extends WithId, WithProperties, WithDescription {

  /**
   * Get the item which this content relates to.
   *
   * @return item
   */
  Item getItem();

  /**
   * The data associated with this content object
   *
   * @return the data
   */
  D getData();

  /**
   * The class of the data stored in this Content object
   *
   * @return data class
   */
  Class<D> getDataClass();

  /**
   * The top level content interface this object implements
   *
   * @return common content interface
   */
  Class<? extends Content<D>> getContentClass();

  /**
   * The annotation store for this content
   *
   * @return annotation store
   */
  AnnotationStore getAnnotations();

  /** Builder interface to createContent (immutable) Content classes */
  interface Builder<A extends Content<D>, D>
      extends WithPropertiesBuilder<Builder<A, D>>,
          WithFromBuilder<Builder<A, D>, A>,
          WithIdBuilder<Builder<A, D>>,
          WithSave<A> {

    /**
     * Set the description of this content object
     *
     * @param description the content description
     * @return this builder for chaining
     */
    Content.Builder<A, D> withDescription(final String description);

    /**
     * Set the data for this content object
     *
     * @param data the data
     * @return this builder for chaining
     */
    default Content.Builder<A, D> withData(final D data) {
      if (data == null) {
        withData((Supplier<D>) null);
      } else {
        withData(() -> data);
      }
      return this;
    }

    /**
     * Set the data for this content object, accessed via a supplier
     *
     * @param data the data
     * @return this builder for chaining
     */
    Content.Builder<A, D> withData(final Supplier<D> data);
  }
}
