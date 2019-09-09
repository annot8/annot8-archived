/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.source;

import io.annot8.core.components.Source;

public class SourceErrorEvent extends AbstractSourceEvent {

  public SourceErrorEvent(Source source) {
    super(source);
  }
}
