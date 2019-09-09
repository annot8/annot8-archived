/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.pipelines.listeners.TaskListener;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;

public interface Task
    extends Annot8Component, WithId, WithName, Runnable, Listenable<TaskListener> {}
