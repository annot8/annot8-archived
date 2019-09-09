/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.listeners;

import io.annot8.common.pipelines.events.PipeEvent;

@FunctionalInterface
public interface PipeListener {

  void onPipeEvent(PipeEvent event);
}
