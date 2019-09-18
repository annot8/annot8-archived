/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components;

import io.annot8.api.settings.Settings;

/**
 * Helper interface for using {@link Annot8ComponentDescriptor} with {@link Processor} interfaces
 *
 * @param <T> The Annot8 processor described by this descriptor
 * @param <S> The settings used by this processor, or {@link io.annot8.api.settings.NoSettings} if
 *     the component doesn't use settings
 */
public interface ProcessorDescriptor<T extends Processor, S extends Settings>
    extends Annot8ComponentDescriptor<T, S> {}
