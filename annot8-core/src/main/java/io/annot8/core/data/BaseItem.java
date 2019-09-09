/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.data;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.exceptions.UnsupportedContentException;
import io.annot8.core.helpers.WithGroups;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithMutableProperties;

/** Base item interface from which all item implementations extend. */
public interface BaseItem extends WithId, WithMutableProperties, WithGroups {

  Optional<String> getParent();

  default boolean hasParent() {
    return getParent().isPresent();
  }

  /**
   * Check if content name exists
   *
   * @return true if this item has a content object with the specified name
   */
  default boolean hasContentOfName(final String name) {
    return listNames().anyMatch(name::equals);
  }

  /**
   * The names of the content objects currently held within this item
   *
   * @return content names
   */
  default Stream<String> listNames() {
    return getContents().map(Content::getName);
  }

  /**
   * The content object for the specified id
   *
   * @param id the content id
   * @return the content if it exists
   */
  Optional<Content<?>> getContent(final String id);

  /**
   * The content object for the specified name
   *
   * @param name the content name
   * @return the content if it exists
   */
  default Stream<Content<?>> getContentByName(final String name) {
    return getContents().filter(c -> c.getName().equals(name));
  }

  /**
   * All content objects contained within this item
   *
   * @return all content
   */
  Stream<Content<?>> getContents();

  /**
   * All content objects of the specified class contained within this item
   *
   * @param clazz the content class to filter against
   * @return content
   */
  default <T extends Content<?>> Stream<T> getContents(final Class<T> clazz) {
    return getContents().filter(clazz::isInstance).map(clazz::cast);
  }

  /**
   * Create a new content builder to generate content.
   *
   * @param clazz the top level content type required
   * @return content builder
   * @throws UnsupportedContentException if the clazz can't be created
   */
  <C extends Content<D>, D> Content.Builder<C, D> create(Class<C> clazz)
      throws UnsupportedContentException;

  /**
   * Remove the specified content object from this item
   *
   * @param id the content id
   */
  void removeContent(final String id);

  /**
   * Stop processing this item any further.
   *
   * <p>Note that it is up to the underlying implementation as to whether they delete existing
   * output from this item or not.
   */
  void discard();

  /**
   * If this item is to be discarded at the end of current processing
   *
   * @return true if discarded
   */
  boolean isDiscarded();
}
