/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.helpers;

import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@FunctionalInterface
public interface WithProcessItem {

  /** Process the given item */
  ProcessorResponse process(final Item item) throws Annot8Exception;
}
