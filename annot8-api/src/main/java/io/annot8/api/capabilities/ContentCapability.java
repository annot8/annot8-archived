/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import io.annot8.api.data.Content;

/**
 * Describes content that is created, processed or deleted
 */
public interface ContentCapability extends Capability {
  /**
   * The type of content
   */
  Class<? extends Content> getType();
}
