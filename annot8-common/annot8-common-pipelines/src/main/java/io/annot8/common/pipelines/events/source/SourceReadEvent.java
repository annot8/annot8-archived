/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.source;

import io.annot8.core.components.Source;

public class SourceReadEvent extends AbstractSourceEvent {

  public SourceReadEvent(Source source) {
    super(source);
  }
}
