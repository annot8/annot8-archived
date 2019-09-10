/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.settings.Settings;

/**
 * A reusable resource (for example a database connection, or a preloaded dataset) that can be used
 * by other components.
 *
 * @param <S> settings class
 */
public interface Resource<S extends Settings> extends Annot8Component<S> {}
