/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.source;

import io.annot8.core.components.Source;

public class SourceDoneEvent extends AbstractSourceEvent {

  public SourceDoneEvent(Source source) {
    super(source);
  }
}
