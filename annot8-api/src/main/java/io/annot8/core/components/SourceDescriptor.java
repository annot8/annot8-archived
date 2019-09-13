/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.settings.Settings;

public interface SourceDescriptor<T extends Source, S extends Settings>
    extends Annot8ComponentDescriptor<T, S> {}
