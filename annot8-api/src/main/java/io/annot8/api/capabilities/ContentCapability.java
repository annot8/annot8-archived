/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

import io.annot8.api.data.Content;

public interface ContentCapability extends Capability {
  Class<? extends Content> getType();
}
