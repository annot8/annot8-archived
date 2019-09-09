/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.pipelines.listeners.JobListener;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;

public interface Job extends Runnable, Listenable<JobListener>, WithId, WithName, AutoCloseable {

  @Override
  default void close() {
    // Do nothing
  }
}
