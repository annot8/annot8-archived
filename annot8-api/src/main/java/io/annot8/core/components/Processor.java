/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components;

import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

/**
 * Base processor interface from which all processors extend.
 *
 * <p>Processors do work on an item, such as adding new annotations, or creating new content.
 */
public interface Processor extends Annot8Component {

  /** Process the given item */
  ProcessorResponse process(final Item item);
}
