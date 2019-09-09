/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public interface Merge extends Annot8Component {

  boolean receive(Item item) throws Annot8Exception;

  void setOutput(WithProcessItem pipe);
}
