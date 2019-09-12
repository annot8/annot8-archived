/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import io.annot8.core.data.Content;

public class ContentCapability {
  private final Class<? extends Content> type;

  public ContentCapability(Class<? extends Content> type) {
    this.type = type;
  }

  public Class<? extends Content> getType() {
    return type;
  }
}
