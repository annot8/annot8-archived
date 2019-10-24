/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.bounds;

import io.annot8.api.data.Content;
import java.util.Optional;

/** Base bounds interface from which all other bounds extend. */
public interface Bounds {

  /**
   * Get the subset of data which is covered by these bounds, returning the data in the native
   * format of the Content.
   *
   * @param <C> content class
   * @param <D> data class
   * @param content the content the bounds should be applied to
   * @return optional empty if there is nothing covered.
   */
  default <D, C extends Content<D>> Optional<D> getData(C content) {
    try {
      return getData(content, content.getDataClass());
    } catch (ClassCastException cce) {
      return Optional.empty();
    }
  }

  /**
   * Get the subset of data which is covered by these bounds.
   *
   * <p>Most bounds will likely processor only one (or very few) required class and data class.
   *
   * @param <C> content class
   * @param <D> data class
   * @param <R> required data class
   * @param content the content the bounds should be applied to
   * @param requiredClass the type of data required
   * @return optional empty if there is nothing covered, or if the combinations are not supported.
   */
  <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass);

  /**
   * Are these bounds valid for the provided content?
   *
   * <p>This means that for example the type of bounds are applicable for the data (ie bounds
   * designed for text are not applicable to image data) and that specifically this bounds is within
   * the content. (ie in the of text the bounds don't lie outside the range of the text).
   *
   * <p>Note that returning true here does not imply that getData will not be empty. The bounds may
   * apply to the content but they may still cover no data.
   *
   * @param <C> content class
   * @param <D> data class
   * @param content the content to check
   * @return true is this bounds can be used with this content
   */
  <D, C extends Content<D>> boolean isValid(C content);
}
