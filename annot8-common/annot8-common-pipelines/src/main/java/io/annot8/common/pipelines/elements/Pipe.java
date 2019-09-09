/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithName;
import io.annot8.core.helpers.WithProcessItem;

public interface Pipe
    extends WithName, WithProcessItem, Annot8Component, Listenable<PipeListener> {}
