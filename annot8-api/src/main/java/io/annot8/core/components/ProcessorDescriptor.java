/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.settings.Settings;

public interface ProcessorDescriptor<T extends Processor, S extends Settings>
    extends Annot8ComponentDescriptor<T, S> {}
